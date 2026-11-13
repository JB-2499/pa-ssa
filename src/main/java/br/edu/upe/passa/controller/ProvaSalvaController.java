package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.dto.ProvaSalvaResponseDTO;
import br.edu.upe.passa.service.ProvaSalvaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provas-salvas")
@RequiredArgsConstructor
public class ProvaSalvaController {

    private final ProvaSalvaService provaSalvaService;

    @PostMapping("/{idUsuario}/{idProva}")
    public ResponseEntity<ProvaSalvaResponseDTO> salvar(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idProva) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProvaSalvaResponseDTO.fromEntity(
                        provaSalvaService.salvar(idUsuario, idProva)
                ));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProvaSalvaResponseDTO>> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        List<ProvaSalvaResponseDTO> provasSalvas = provaSalvaService.listarPorUsuario(idUsuario).stream()
                .map(ProvaSalvaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(provasSalvas);
    }

    @DeleteMapping("/{idUsuario}/{idProva}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idProva) {

        provaSalvaService.remover(idUsuario, idProva);
        return ResponseEntity.noContent().build();
    }
}