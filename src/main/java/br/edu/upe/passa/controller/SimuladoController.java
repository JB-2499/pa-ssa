package br.edu.upe.passa.controller;

import br.edu.upe.passa.dto.SimuladoRequestDTO;
import br.edu.upe.passa.dto.SimuladoResponseDTO;
import br.edu.upe.passa.entity.Simulado;
import br.edu.upe.passa.service.SimuladoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/simulados")
@RequiredArgsConstructor
public class SimuladoController {

    private final SimuladoService simuladoService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public SimuladoResponseDTO criar(@Valid @RequestBody SimuladoRequestDTO dto) {
        Simulado simulado = simuladoService.criar(dto.usuarioId(), dto.nome(), dto.tempoLimiteMinutos());
        return SimuladoResponseDTO.fromEntity(simulado);
    }

    @GetMapping("/{id}")
    public SimuladoResponseDTO buscarPorId(@PathVariable Integer id) {
        return SimuladoResponseDTO.fromEntity(simuladoService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<SimuladoResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return simuladoService.listarPorUsuario(usuarioId).stream()
                .map(SimuladoResponseDTO::fromEntity)
                .toList();
    }

    @PutMapping("/{id}")
    public SimuladoResponseDTO atualizar(@PathVariable Integer id, @Valid @RequestBody SimuladoRequestDTO dto) {
        Simulado simulado = simuladoService.atualizar(id, dto.nome(), dto.tempoLimiteMinutos());
        return SimuladoResponseDTO.fromEntity(simulado);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        simuladoService.deletar(id);
    }
}
