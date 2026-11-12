package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.service.ProvaService;

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
    public ResponseEntity<List<Prova>> listarTodas() {
        return ResponseEntity.ok(provaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Prova> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(provaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Prova> salvar(
            @RequestBody Prova prova) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(provaService.salvar(prova));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        provaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}