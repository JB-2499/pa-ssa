package br.edu.upe.passa.controller;

import br.edu.upe.passa.dto.MetaRequestDTO;
import br.edu.upe.passa.dto.MetaResponseDTO;
import br.edu.upe.passa.entity.Meta;
import br.edu.upe.passa.service.MetaService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/metas")
@RequiredArgsConstructor
public class MetaController {

    private final MetaService metaService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public MetaResponseDTO criar(@Valid @RequestBody MetaRequestDTO dto) {
        Meta meta = metaService.criar(dto.usuarioId(), dto.areaId(), dto.taxaDesejada(), dto.dataAlvo());
        return MetaResponseDTO.fromEntity(meta);
    }

    @GetMapping("/{id}")
    public MetaResponseDTO buscarPorId(@PathVariable Integer id) {
        return MetaResponseDTO.fromEntity(metaService.buscarPorId(id));
    }

    @GetMapping("/usuario/{usuarioId}")
    public List<MetaResponseDTO> listarPorUsuario(@PathVariable Integer usuarioId) {
        return metaService.listarPorUsuario(usuarioId).stream()
                .map(MetaResponseDTO::fromEntity)
                .toList();
    }

    @PutMapping("/{id}")
    public MetaResponseDTO atualizar(@PathVariable Integer id, @Valid @RequestBody MetaRequestDTO dto) {
        Meta meta = metaService.atualizar(id, dto.taxaDesejada(), dto.dataAlvo());
        return MetaResponseDTO.fromEntity(meta);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        metaService.deletar(id);
    }
}
