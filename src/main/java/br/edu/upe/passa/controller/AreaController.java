package br.edu.upe.passa.controller;


import br.edu.upe.passa.entity.Area;
import br.edu.upe.passa.service.AreaService;

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
    public ResponseEntity<List<Area>> listarTodas() {
        return ResponseEntity.ok(areaService.listarTodas());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Area> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(areaService.buscarPorId(id));
    }

    @PostMapping
    public ResponseEntity<Area> salvar(
            @RequestBody Area area) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(areaService.salvar(area));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Area> atualizar(
            @PathVariable Integer id,
            @RequestBody Area area) {

        return ResponseEntity.ok(areaService.atualizar(id, area));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {

        areaService.excluir(id);

        return ResponseEntity.noContent().build();
    }
}