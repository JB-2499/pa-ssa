package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Questao;
import br.edu.upe.passa.service.QuestaoService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/questoes")
@RequiredArgsConstructor
public class QuestaoController {

    private final QuestaoService questaoService;

    @GetMapping
    public ResponseEntity<List<Questao>> listarTodas() {
        return ResponseEntity.ok(questaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Questao> buscarPorId(
            @PathVariable Integer id) {

        return ResponseEntity.ok(questaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Questao> salvar(
            @RequestBody Questao questao) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questaoService.salvar(questao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Questao> atualizar(
            @PathVariable Integer id,
            @RequestBody Questao questao) {

        return ResponseEntity.ok(
                questaoService.atualizar(id, questao)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        questaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}