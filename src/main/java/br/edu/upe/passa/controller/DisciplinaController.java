package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Disciplina;
import br.edu.upe.passa.service.DisciplinaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/disciplinas")
@RequiredArgsConstructor
public class DisciplinaController {

    private final DisciplinaService disciplinaService;

    @GetMapping
    public ResponseEntity<List<Disciplina>> listarTodas() {
        return ResponseEntity.ok(disciplinaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Disciplina> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(disciplinaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Disciplina> salvar(
            @RequestBody Disciplina disciplina) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(disciplinaService.salvar(disciplina));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Disciplina> atualizar(
            @PathVariable Integer id,
            @RequestBody Disciplina disciplina) {

        return ResponseEntity.ok(
                disciplinaService.atualizar(id, disciplina)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        disciplinaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}