package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Topico;
import br.edu.upe.passa.repository.TopicoRepository;
import br.edu.upe.passa.service.TopicoService;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TopicoServiceImpl implements TopicoService {

    private final TopicoRepository topicoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Topico> listarTodos() {
        return topicoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Topico buscarPorId(Integer id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tópico não encontrado"
                ));
    }

    @Override
    @Transactional
    public Topico salvar(Topico topico) {

        if (topico.getDisciplina() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "O tópico precisa estar vinculado a uma disciplina"
            );
        }

        return topicoRepository.save(topico);
    }

    @Override
    @Transactional
    public Topico atualizar(Integer id, Topico topico) {

        Topico existente = buscarPorId(id);

        existente.setNome(topico.getNome());
        existente.setDisciplina(topico.getDisciplina());

        return topicoRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Topico topico = buscarPorId(id);
        topicoRepository.delete(topico);
    }
}