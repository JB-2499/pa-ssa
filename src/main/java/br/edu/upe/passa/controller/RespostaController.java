package br.edu.upe.passa.controller;

import br.edu.upe.passa.dto.RespostaRequestDTO;
import br.edu.upe.passa.dto.RespostaResponseDTO;
import br.edu.upe.passa.entity.Resposta;
import br.edu.upe.passa.service.RespostaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/respostas")
@RequiredArgsConstructor
public class RespostaController {

    private final RespostaService respostaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public RespostaResponseDTO registrar(@Valid @RequestBody RespostaRequestDTO dto) {
        Resposta resposta = respostaService.registrar(
                dto.usuarioId(), dto.questaoId(), dto.letraMarcada(), dto.tentativaId());
        return RespostaResponseDTO.fromEntity(resposta);
    }

    @GetMapping("/{id}")
    public RespostaResponseDTO buscarPorId(@PathVariable Integer id) {
        return RespostaResponseDTO.fromEntity(respostaService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<RespostaResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return respostaService.listarPorUsuario(usuarioId).stream()
                .map(RespostaResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/tentativa/{tentativaId}")
    public List<RespostaResponseDTO> listarPorTentativa(@PathVariable Integer tentativaId) {
        return respostaService.listarPorTentativa(tentativaId).stream()
                .map(RespostaResponseDTO::fromEntity)
                .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        respostaService.deletar(id);
    }
}
