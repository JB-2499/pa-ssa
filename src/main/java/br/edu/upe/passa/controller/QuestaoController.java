package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.QuestaoEntity;
import br.edu.upe.passa.service.interfaceService.QuestaoService;

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
    public ResponseEntity<List<QuestaoEntity>> listarTodas() {
        return ResponseEntity.ok(questaoService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<QuestaoEntity> buscarPorId(
            @PathVariable Long id) {

        return ResponseEntity.ok(questaoService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<QuestaoEntity> salvar(
            @RequestBody QuestaoEntity questao) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(questaoService.salvar(questao));
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestaoEntity> atualizar(
            @PathVariable Long id,
            @RequestBody QuestaoEntity questao) {

        return ResponseEntity.ok(
                questaoService.atualizar(id, questao)
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        questaoService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}