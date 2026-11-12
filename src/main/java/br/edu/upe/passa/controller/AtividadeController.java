package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.dto.AtividadeRequestDTO;
import br.edu.upe.passa.entity.dto.AtividadeResponseDTO;
import br.edu.upe.passa.entity.Atividade;
import br.edu.upe.passa.service.AtividadeService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/atividades")
@RequiredArgsConstructor
public class AtividadeController {

    private final AtividadeService atividadeService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AtividadeResponseDTO registrar(@Valid @RequestBody AtividadeRequestDTO dto) {
        Atividade atividade = atividadeService.registrar(
                dto.usuarioId(), dto.areaId(), dto.tipo(), dto.ocorridaEm());
        return AtividadeResponseDTO.fromEntity(atividade);
    }

    @GetMapping("/{id}")
    public AtividadeResponseDTO buscarPorId(@PathVariable Integer id) {
        return AtividadeResponseDTO.fromEntity(atividadeService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<AtividadeResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return atividadeService.listarPorUsuario(usuarioId).stream()
                .map(AtividadeResponseDTO::fromEntity)
                .toList();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        atividadeService.deletar(id);
    }
}
