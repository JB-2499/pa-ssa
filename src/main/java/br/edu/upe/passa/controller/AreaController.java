package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Area;
import br.edu.upe.passa.entity.dto.AreaRequestDTO;
import br.edu.upe.passa.entity.dto.AreaResponseDTO;
import br.edu.upe.passa.service.AreaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<AreaResponseDTO>> listarTodas() {
        List<AreaResponseDTO> areas = areaService.listarTodas().stream()
                .map(AreaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(areas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AreaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                AreaResponseDTO.fromEntity(areaService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<AreaResponseDTO> salvar(@Valid @RequestBody AreaRequestDTO dto) {
        Area salva = areaService.salvar(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AreaResponseDTO.fromEntity(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AreaResponseDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AreaRequestDTO dto) {

        Area atualizada = areaService.atualizar(id, toEntity(dto));
        return ResponseEntity.ok(AreaResponseDTO.fromEntity(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        areaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private Area toEntity(AreaRequestDTO dto) {
        Area area = new Area();
        area.setNome(dto.nome());
        return area;
    }
}