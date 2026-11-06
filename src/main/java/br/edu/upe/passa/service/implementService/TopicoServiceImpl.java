package br.edu.upe.passa.service.implementService;

import br.edu.upe.passa.entity.TopicoEntity;
import br.edu.upe.passa.repository.TopicoRepository;
import br.edu.upe.passa.service.interfaceService.TopicoService;

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
    public List<TopicoEntity> listarTodos() {
        return topicoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public TopicoEntity buscarPorId(Long id) {
        return topicoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Tópico não encontrado"
                ));
    }

    @Override
    @Transactional
    public TopicoEntity salvar(TopicoEntity topico) {

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
    public TopicoEntity atualizar(Long id, TopicoEntity topico) {

        TopicoEntity existente = buscarPorId(id);

        existente.setNome(topico.getNome());
        existente.setDisciplina(topico.getDisciplina());

        return topicoRepository.save(existente);
    }

    @Override
    @Transactional
    public void excluir(Long id) {
        TopicoEntity topico = buscarPorId(id);
        topicoRepository.delete(topico);
    }
}