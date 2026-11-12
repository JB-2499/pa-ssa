package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.dto.TentativaRequestDTO;
import br.edu.upe.passa.entity.dto.TentativaResponseDTO;
import br.edu.upe.passa.entity.Tentativa;
import br.edu.upe.passa.service.TentativaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/tentativas")
@RequiredArgsConstructor
public class TentativaController {

    private final TentativaService tentativaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TentativaResponseDTO iniciar(@Valid @RequestBody TentativaRequestDTO dto) {
        Tentativa tentativa = tentativaService.iniciar(dto.usuarioId(), dto.simuladoId(), dto.provaId());
        return TentativaResponseDTO.fromEntity(tentativa);
    }

    @GetMapping("/{id}")
    public TentativaResponseDTO buscarPorId(@PathVariable Integer id) {
        return TentativaResponseDTO.fromEntity(tentativaService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<TentativaResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return tentativaService.listarPorUsuario(usuarioId).stream()
                .map(TentativaResponseDTO::fromEntity)
                .toList();
    }

    @PatchMapping("/{id}/finalizar")
    public TentativaResponseDTO finalizar(@PathVariable Integer id) {
        return TentativaResponseDTO.fromEntity(tentativaService.finalizar(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        tentativaService.deletar(id);
    }
}
