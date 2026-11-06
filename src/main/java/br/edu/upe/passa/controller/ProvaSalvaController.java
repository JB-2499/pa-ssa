package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.ProvaSalvaEntity;
import br.edu.upe.passa.service.interfaces.ProvaSalvaService;

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
    public ResponseEntity<ProvaSalvaEntity> salvar(
            @PathVariable Long idUsuario,
            @PathVariable Long idProva) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(provaSalvaService.salvar(idUsuario, idProva));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProvaSalvaEntity>> listarPorUsuario(
            @PathVariable Long idUsuario) {

        return ResponseEntity.ok(
                provaSalvaService.listarPorUsuario(idUsuario)
        );
    }

    @DeleteMapping("/{idUsuario}/{idProva}")
    public ResponseEntity<Void> remover(
            @PathVariable Long idUsuario,
            @PathVariable Long idProva) {

        provaSalvaService.remover(idUsuario, idProva);

        return ResponseEntity.noContent().build();
    }
}