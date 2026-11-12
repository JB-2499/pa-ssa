package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Topico;
import br.edu.upe.passa.service.TopicoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/topicos")
@RequiredArgsConstructor
public class TopicoController {

    private final TopicoService topicoService;

    @GetMapping
    public ResponseEntity<List<Topico>> listarTodos() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Topico> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(topicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Topico> salvar(
            @RequestBody Topico topico) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(topicoService.salvar(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Topico> atualizar(
            @PathVariable Integer id,
            @RequestBody Topico topico) {

        return ResponseEntity.ok(
                topicoService.atualizar(id, topico)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        topicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}