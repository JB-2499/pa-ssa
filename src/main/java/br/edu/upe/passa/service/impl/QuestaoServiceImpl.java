package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Questao;
import br.edu.upe.passa.entity.Alternativa;
import br.edu.upe.passa.repository.QuestaoRepository;
import br.edu.upe.passa.service.QuestaoService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestaoServiceImpl implements QuestaoService {

    private final QuestaoRepository questaoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Questao> listarTodas() {
        return questaoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Questao buscarPorId(Integer id) {
        return questaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Questão não encontrada"
                ));
    }

    @Override
    @Transactional
    public Questao salvar(Questao questao) {

        if (questao.getProva() == null || questao.getTopico() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A questão precisa possuir uma prova e um tópico"
            );
        }

        if (questao.getAlternativas() == null ||
                questao.getAlternativas().isEmpty()) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "A questão precisa possuir alternativas"
            );
        }

        for (Alternativa alternativa : questao.getAlternativas()) {
            alternativa.setQuestao(questao);
        }

        return questaoRepository.save(questao);
    }

    @Override
    @Transactional
    public Questao atualizar(Integer id, Questao questao) {

        Questao existente = buscarPorId(id);

        existente.setNumero(questao.getNumero());
        existente.setEnunciado(questao.getEnunciado());
        existente.setLetraCorreta(questao.getLetraCorreta());
        existente.setImagemUrl(questao.getImagemUrl());
        existente.setProva(questao.getProva());
        existente.setTopico(questao.getTopico());

        return questaoRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Questao questao = buscarPorId(id);
        questaoRepository.delete(questao);
    }
}