package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Disciplina;
import br.edu.upe.passa.repository.DisciplinaRepository;
import br.edu.upe.passa.service.DisciplinaService;
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
    public List<Disciplina> listarTodas() {
        return disciplinaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Disciplina buscarPorId(Integer id) {
        return disciplinaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Disciplina não encontrada"
                ));
    }

    @Override
    @Transactional
    public Disciplina salvar(Disciplina disciplina) {

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
    public Disciplina atualizar(Integer id, Disciplina disciplina) {

        Disciplina existente = buscarPorId(id);

        existente.setNome(disciplina.getNome());
        existente.setArea(disciplina.getArea());

        return disciplinaRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Disciplina disciplina = buscarPorId(id);
        disciplinaRepository.delete(disciplina);
    }
}