package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Area;
import br.edu.upe.passa.repository.AreaRepository;
import br.edu.upe.passa.service.AreaService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AreaServiceImpl implements AreaService {

    private final AreaRepository areaRepository;

    @Override
    public List<Area> listarTodas() {
        return areaRepository.findAll();
    }

    @Override
    public Area buscarPorId(Integer id) {
        return areaRepository.findById(id).orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "não encontrada"
                        )
                );
    }

    @Override
    @Transactional
    public Area salvar(Area area) {

        return areaRepository.save(area);
    }

    @Override
    @Transactional
    public Area atualizar(Integer id, Area area) {

        Area existente = buscarPorId(id);

        existente.setNome(area.getNome());

        return areaRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Area area = buscarPorId(id);
        areaRepository.delete(area);
    }
}