package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.entity.dto.ProvaRequestDTO;
import br.edu.upe.passa.entity.dto.ProvaResponseDTO;
import br.edu.upe.passa.service.ProvaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/provas")
@RequiredArgsConstructor
public class ProvaController {

    private final ProvaService provaService;

    @GetMapping
    public ResponseEntity<List<ProvaResponseDTO>> listarTodas() {
        List<ProvaResponseDTO> provas = provaService.listarTodas().stream()
                .map(ProvaResponseDTO::fromEntity)
                .toList();
        return ResponseEntity.ok(provas);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProvaResponseDTO> buscarPorId(@PathVariable Integer id) {
        return ResponseEntity.ok(
                ProvaResponseDTO.fromEntity(provaService.buscarPorId(id))
        );
    }

    @PostMapping
    public ResponseEntity<ProvaResponseDTO> salvar(@Valid @RequestBody ProvaRequestDTO dto) {
        Prova prova = new Prova();
        prova.setAno(dto.ano());
        prova.setSerie(dto.serie());
        prova.setTempoMinutos(dto.tempoMinutos());

        Prova salva = provaService.salvar(prova);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(ProvaResponseDTO.fromEntity(salva));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> excluir(@PathVariable Integer id) {
        provaService.excluir(id);
        return ResponseEntity.noContent().build();
    }
}