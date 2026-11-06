package br.edu.upe.passa.controller;


import br.edu.upe.passa.entity.AreaEntity;
import br.edu.upe.passa.service.interfaceService.AreaService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/areas")
@RequiredArgsConstructor
public class AreaController {

    private final AreaService areaService;

    @GetMapping
    public ResponseEntity<List<AreaEntity>> listarTodas() {
        return ResponseEntity.ok(areaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaEntity> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(areaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<AreaEntity> salvar(
            @RequestBody AreaEntity area) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(areaService.salvar(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaEntity> atualizar(
            @PathVariable Long id,
            @RequestBody AreaEntity area) {

        return ResponseEntity.ok(areaService.atualizar(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Long id) {

        areaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}