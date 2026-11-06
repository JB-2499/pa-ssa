package br.edu.upe.passa.service.implementService;

import br.edu.upe.passa.entity.AreaEntity;
import br.edu.upe.passa.repository.AreaRepository;
import br.edu.upe.passa.service.interfaceService.AreaService;
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
    @Transactional(readOnly = true)
    public List<AreaEntity> listarTodas() {
        return areaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)

    public AreaEntity buscarPorId(Long id) {
        return areaRepository.findById(id)
                .orElseThrow(() ->
                        new ResponseStatusException(
                                HttpStatus.NOT_FOUND,
                                "não encontrada"
                        )
                );
    }

    @Override
    @Transactional
    public AreaEntity salvar(AreaEntity area) {

        return areaRepository.save(area);
    }

    @Override
    @Transactional
    public AreaEntity atualizar(Long id, AreaEntity area) {

        AreaEntity existente = buscarPorId(id);

        existente.setNome(area.getNome());

        return areaRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        AreaEntity area = buscarPorId(id);
        areaRepository.delete(area);
    }
}