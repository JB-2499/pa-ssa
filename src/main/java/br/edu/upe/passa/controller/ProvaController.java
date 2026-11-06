package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.ProvaEntity;
import br.edu.upe.passa.service.interfaceService.ProvaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provas")
@RequiredArgsConstructor
public class ProvaController {

    private final ProvaService provaService;

    @GetMapping
    public ResponseEntity<List<ProvaEntity>> listarTodas() {
        return ResponseEntity.ok(provaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvaEntity> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(provaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<ProvaEntity> salvar(
            @RequestBody ProvaEntity prova) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(provaService.salvar(prova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        provaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}