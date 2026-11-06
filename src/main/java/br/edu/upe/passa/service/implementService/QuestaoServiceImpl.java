package br.edu.upe.passa.service.implementService;

import br.edu.upe.passa.entity.QuestaoEntity;
import br.edu.upe.passa.entity.AlternativaEntity;
import br.edu.upe.passa.repository.QuestaoRepository;
import br.edu.upe.passa.service.interfaceService.QuestaoService;
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
    public List<QuestaoEntity> listarTodas() {
        return questaoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public QuestaoEntity buscarPorId(Long id) {
        return questaoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Questão não encontrada"
                ));
    }

    @Override
    @Transactional
    public QuestaoEntity salvar(QuestaoEntity questao) {

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

        for (AlternativaEntity alternativa : questao.getAlternativas()) {
            alternativa.setQuestao(questao);
        }

        return questaoRepository.save(questao);
    }

    @Override
    @Transactional
    public QuestaoEntity atualizar(Long id, QuestaoEntity questao) {

        QuestaoEntity existente = buscarPorId(id);

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
    public void excluir(Long id) {
        QuestaoEntity questao = buscarPorId(id);
        questaoRepository.delete(questao);
    }
}