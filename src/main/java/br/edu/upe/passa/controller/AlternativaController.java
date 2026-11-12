package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.dto.AlternativaRequestDTO;
import br.edu.upe.passa.entity.dto.AlternativaResponseDTO;
import br.edu.upe.passa.service.AlternativaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/alternativas")
@RequiredArgsConstructor
public class AlternativaController {

    private final AlternativaService alternativaService;

    @GetMapping
    public ResponseEntity<List<AlternativaResponseDTO>> listar(
            @RequestParam(required = false) Integer questaoId) {

        var alternativas = (questaoId == null)
                ? alternativaService.listarTodas()
                : alternativaService.listarPorQuestao(questaoId);

        return ResponseEntity.ok(
                alternativas.stream().map(AlternativaResponseDTO::fromEntity).toList()
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<AlternativaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                AlternativaResponseDTO.fromEntity(alternativaService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<AlternativaResponseDTO> criar(
            @Valid @RequestBody AlternativaRequestDTO dto) {

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(AlternativaResponseDTO.fromEntity(alternativaService.criar(dto)));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AlternativaResponseDTO> atualizar(
            @PathVariable Integer id,
            @Valid @RequestBody AlternativaRequestDTO dto) {

        return ResponseEntity.ok(
                AlternativaResponseDTO.fromEntity(alternativaService.atualizar(id, dto))
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        alternativaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}