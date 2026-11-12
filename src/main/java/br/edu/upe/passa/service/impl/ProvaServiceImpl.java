package br.edu.upe.passa.service.impl;


import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.repository.ProvaRepository;

import br.edu.upe.passa.service.ProvaService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvaServiceImpl implements ProvaService {

    private final ProvaRepository provaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Prova> listarTodas() {
        return provaRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Prova buscarPorId(Integer id) {
        return provaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prova não encontrada"
                ));
    }

    @Override
    @Transactional
    public Prova salvar(Prova prova) {

        if (prova.getAno() == null || prova.getSerie() == null) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    "Informe o ano e a série da prova"
            );
        }

        return provaRepository.save(prova);
    }

    @Override
    @Transactional
    public void excluir(Integer id) {
        Prova prova = buscarPorId(id);
        provaRepository.delete(prova);
    }
}
