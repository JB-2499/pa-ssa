//Corrigir colocando os DTO


package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.ProvaSalva;
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
    public ResponseEntity<ProvaSalva> salvar(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idProva) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(provaSalvaService.salvar(idUsuario, idProva));
    }

    @GetMapping("/usuario/{idUsuario}")
    public ResponseEntity<List<ProvaSalva>> listarPorUsuario(
            @PathVariable Integer idUsuario) {

        return ResponseEntity.ok(
                provaSalvaService.listarPorUsuario(idUsuario)
        );
    }

    @DeleteMapping("/{idUsuario}/{idProva}")
    public ResponseEntity<Void> remover(
            @PathVariable Integer idUsuario,
            @PathVariable Integer idProva) {

        provaSalvaService.remover(idUsuario, idProva);

        return ResponseEntity.noContent().build();
    }
}