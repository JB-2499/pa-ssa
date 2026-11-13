package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Area;
import br.edu.upe.passa.entity.Disciplina;
import br.edu.upe.passa.entity.dto.DisciplinaRequestDTO;
import br.edu.upe.passa.entity.dto.DisciplinaResponseDTO;
import br.edu.upe.passa.service.DisciplinaService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<DisciplinaResponseDTO>> listarTodas() {
        List<DisciplinaResponseDTO> disciplinas = disciplinaService.listarTodas().stream()
                .map(DisciplinaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(disciplinas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                DisciplinaResponseDTO.fromEntity(disciplinaService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<DisciplinaResponseDTO> salvar(@Valid @RequestBody DisciplinaRequestDTO dto) {
        Disciplina salva = disciplinaService.salvar(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(DisciplinaResponseDTO.fromEntity(salva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<DisciplinaResponseDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody DisciplinaRequestDTO dto) {

        Disciplina atualizada = disciplinaService.atualizar(id, toEntity(dto));
        return ResponseEntity.ok(DisciplinaResponseDTO.fromEntity(atualizada));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        disciplinaService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private Disciplina toEntity(DisciplinaRequestDTO dto) {
        Area area = new Area();
        area.setId(dto.areaId());

        Disciplina disciplina = new Disciplina();
        disciplina.setNome(dto.nome());
        disciplina.setArea(area);
        return disciplina;
    }
}