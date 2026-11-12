package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.dto.TokenVerificacaoRequestDTO;
import br.edu.upe.passa.entity.dto.TokenVerificacaoResponseDTO;
import br.edu.upe.passa.entity.TokenVerificacao;
import br.edu.upe.passa.service.TokenVerificacaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tokens")
@RequiredArgsConstructor
public class TokenVerificacaoController {

    private final TokenVerificacaoService tokenVerificacaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TokenVerificacaoResponseDTO gerar(@Valid @RequestBody TokenVerificacaoRequestDTO dto) {
        TokenVerificacao token = tokenVerificacaoService.gerar(dto.usuarioId(), dto.tipo(), dto.emailNovo());
        return TokenVerificacaoResponseDTO.fromEntity(token);
    }

    @PatchMapping("/validar/{codigo}")
    public TokenVerificacaoResponseDTO validar(@PathVariable String codigo) {
        TokenVerificacao token = tokenVerificacaoService.validar(codigo);
        return TokenVerificacaoResponseDTO.fromEntity(token);
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<TokenVerificacaoResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return tokenVerificacaoService.listarPorUsuario(usuarioId).stream()
                .map(TokenVerificacaoResponseDTO::fromEntity)
                .toList();
    }
}
