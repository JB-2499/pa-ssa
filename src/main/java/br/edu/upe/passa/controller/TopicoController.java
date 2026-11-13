package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Disciplina;
import br.edu.upe.passa.entity.Topico;
import br.edu.upe.passa.entity.dto.TopicoRequestDTO;
import br.edu.upe.passa.entity.dto.TopicoResponseDTO;
import br.edu.upe.passa.service.TopicoService;
import jakarta.validation.Valid;
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
    public ResponseEntity<List<TopicoResponseDTO>> listarTodos() {
        List<TopicoResponseDTO> topicos = topicoService.listarTodos().stream()
                .map(TopicoResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(topicos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                TopicoResponseDTO.fromEntity(topicoService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<TopicoResponseDTO> salvar(@Valid @RequestBody TopicoRequestDTO dto) {
        Topico salvo = topicoService.salvar(toEntity(dto));
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(TopicoResponseDTO.fromEntity(salvo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<TopicoResponseDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody TopicoRequestDTO dto) {

        Topico atualizado = topicoService.atualizar(id, toEntity(dto));
        return ResponseEntity.ok(TopicoResponseDTO.fromEntity(atualizado));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        topicoService.excluir(id);
        return ResponseEntity.noContent().build();
    }

    private Topico toEntity(TopicoRequestDTO dto) {
        Disciplina disciplina = new Disciplina();
        disciplina.setId(dto.disciplinaId());

        Topico topico = new Topico();
        topico.setNome(dto.nome());
        topico.setDisciplina(disciplina);
        return topico;
    }
}