package br.edu.upe.passa.service.implementService;

import br.edu.upe.passa.entity.DisciplinaEntity;
import br.edu.upe.passa.repository.DisciplinaRepository;
import br.edu.upe.passa.service.interfaceService.DisciplinaService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DisciplinaServiceImpl implements DisciplinaService {

    private final DisciplinaRepository disciplinaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<DisciplinaEntity> listarTodas() {
        return disciplinaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public DisciplinaEntity buscarPorId(Long id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"
                ));
    }

    @Override
    @Transactional
    public DisciplinaEntity salvar(DisciplinaEntity disciplina) {

        if (disciplina.getArea() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A disciplina precisa estar vinculada a uma área"
            );
        }

        return disciplinaRepository.save(disciplina);
    }

    @Override
    @Transactional
    public DisciplinaEntity atualizar(Long id, DisciplinaEntity disciplina) {

        DisciplinaEntity existente = buscarPorId(id);

        existente.setNome(disciplina.getNome());
        existente.setArea(disciplina.getArea());

        return disciplinaRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        DisciplinaEntity disciplina = buscarPorId(id);
        disciplinaRepository.delete(disciplina);
    }
}