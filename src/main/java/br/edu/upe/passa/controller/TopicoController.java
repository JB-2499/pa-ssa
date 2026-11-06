package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.TopicoEntity;
import br.edu.upe.passa.service.interfaceService.TopicoService;

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
    public ResponseEntity<List<TopicoEntity>> listarTodos() {
        return ResponseEntity.ok(topicoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoEntity> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(topicoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<TopicoEntity> salvar(
            @RequestBody TopicoEntity topico) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(topicoService.salvar(topico));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoEntity> atualizar(
            @PathVariable Long id,
            @RequestBody TopicoEntity topico) {

        return ResponseEntity.ok(
                topicoService.atualizar(id, topico)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        topicoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}