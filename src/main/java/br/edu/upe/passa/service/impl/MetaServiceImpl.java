package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Area;
import br.edu.upe.passa.entity.Meta;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.AreaRepository;
import br.edu.upe.passa.repository.MetaRepository;
import br.edu.upe.passa.repository.UsuarioRepository;
import br.edu.upe.passa.service.MetaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MetaServiceImpl implements MetaService {

    private final MetaRepository metaRepository;
    private final UsuarioRepository usuarioRepository;
    private final AreaRepository areaRepository;

    @Override
    @Transactional
    public Meta criar(Integer usuarioId, Integer areaId, Integer taxaDesejada, LocalDate dataAlvo) {
        validarTaxaEData(taxaDesejada, dataAlvo);

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
        Area area = areaRepository.findById(areaId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área não encontrada"));

        if (metaRepository.existsByUsuarioIdAndArea_Id(usuarioId, areaId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT,
                    "O usuário já possui uma meta para esta área");
        }

        Meta meta = new Meta();
        meta.setUsuario(usuario);
        meta.setArea(area);
        meta.setTaxaDesejada(taxaDesejada);
        meta.setDataAlvo(dataAlvo);

        return metaRepository.save(meta);
    }

    @Override
    @Transactional(readOnly = true)
    public Meta buscarPorId(Integer id) {
        return metaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Meta não encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Meta> listarPorUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return metaRepository.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional
    public Meta atualizar(Integer id, Integer taxaDesejada, LocalDate dataAlvo) {
        validarTaxaEData(taxaDesejada, dataAlvo);

        Meta meta = buscarPorId(id);
        meta.setTaxaDesejada(taxaDesejada);
        meta.setDataAlvo(dataAlvo);

        return metaRepository.save(meta);
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
        Meta meta = buscarPorId(id);
        metaRepository.delete(meta);
    }

    private void validarTaxaEData(Integer taxaDesejada, LocalDate dataAlvo) {
        if (taxaDesejada == null || taxaDesejada < 1 || taxaDesejada > 100) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A taxa desejada deve estar entre 1 e 100");
        }
        if (dataAlvo != null && !dataAlvo.isAfter(LocalDate.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A data alvo deve ser uma data futura");
        }
    }
}
