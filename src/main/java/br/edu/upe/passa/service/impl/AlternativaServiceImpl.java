package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.dto.AlternativaRequestDTO;
import br.edu.upe.passa.entity.Alternativa;
import br.edu.upe.passa.entity.Questao;
import br.edu.upe.passa.repository.AlternativaRepository;
import br.edu.upe.passa.repository.QuestaoRepository;
import br.edu.upe.passa.service.AlternativaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AlternativaServiceImpl implements AlternativaService {

    private final AlternativaRepository alternativaRepository;
    private final QuestaoRepository questaoRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Alternativa> listarTodas() {
        return alternativaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Alternativa> listarPorQuestao(Integer id) {
        return alternativaRepository.findByQuestao_Id(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Alternativa buscarPorId(Integer id) {
        return alternativaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Alternativa não encontrada"
                ));
    }

    @Override
    @Transactional
    public Alternativa criar(AlternativaRequestDTO dto) {
        Questao questao = questaoRepository.findById(dto.id())
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Questão não encontrada"
                ));

        if (alternativaRepository.existsByIdAndLetra(dto.id(), dto.letra())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A questão já possui uma alternativa com a letra " + dto.letra()
            );
        }

        Alternativa alternativa = new Alternativa();
        alternativa.setQuestao(questao);
        alternativa.setLetra(dto.letra());
        alternativa.setTexto(dto.texto());

        return alternativaRepository.save(alternativa);
    }

    @Override
    @Transactional
    public Alternativa atualizar(Integer id, AlternativaRequestDTO dto) {
        Alternativa existente = buscarPorId(id);

        if (!existente.getQuestao().getId().equals(dto.id())) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Não é permitido mudar a questão de uma alternativa"
            );
        }

        if (!existente.getLetra().equals(dto.letra())
                && alternativaRepository.existsByIdAndLetra(dto.id(), dto.letra())) {
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "A questão já possui uma alternativa com a letra " + dto.letra()
            );
        }

        existente.setLetra(dto.letra());
        existente.setTexto(dto.texto());

        return alternativaRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Alternativa alternativa = buscarPorId(id);
        alternativaRepository.delete(alternativa);
    }
}