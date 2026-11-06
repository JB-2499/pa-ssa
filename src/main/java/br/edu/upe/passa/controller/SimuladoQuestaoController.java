package br.edu.upe.passa.controller;

import br.edu.upe.passa.dto.SimuladoQuestaoOrdemDTO;
import br.edu.upe.passa.dto.SimuladoQuestaoRequestDTO;
import br.edu.upe.passa.dto.SimuladoQuestaoResponseDTO;
import br.edu.upe.passa.service.SimuladoQuestaoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulados/{simuladoId}/questoes")
@RequiredArgsConstructor
public class SimuladoQuestaoController {

    private final SimuladoQuestaoService simuladoQuestaoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SimuladoQuestaoResponseDTO adicionar(@PathVariable Integer simuladoId,
                                                @Valid @RequestBody SimuladoQuestaoRequestDTO dto) {
        return SimuladoQuestaoResponseDTO.fromEntity(
                simuladoQuestaoService.adicionar(simuladoId, dto.questaoId(), dto.ordem()));
    }

    @GetMapping
    public List<SimuladoQuestaoResponseDTO> listar(@PathVariable Integer simuladoId) {
        return simuladoQuestaoService.listarPorSimulado(simuladoId).stream()
                .map(SimuladoQuestaoResponseDTO::fromEntity)
                .toList();
    }

    @PutMapping("/{questaoId}")
    public SimuladoQuestaoResponseDTO atualizarOrdem(@PathVariable Integer simuladoId,
                                                     @PathVariable Integer questaoId,
                                                     @Valid @RequestBody SimuladoQuestaoOrdemDTO dto) {
        return SimuladoQuestaoResponseDTO.fromEntity(
                simuladoQuestaoService.atualizarOrdem(simuladoId, questaoId, dto.ordem()));
    }

    @DeleteMapping("/{questaoId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void remover(@PathVariable Integer simuladoId, @PathVariable Integer questaoId) {
        simuladoQuestaoService.remover(simuladoId, questaoId);
    }
}
