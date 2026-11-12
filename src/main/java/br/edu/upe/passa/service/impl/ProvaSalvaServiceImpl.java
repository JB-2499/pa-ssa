package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.ProvaSalva;
import br.edu.upe.passa.entity.ProvaSalvaId;
import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.entity.Usuario;

import br.edu.upe.passa.repository.ProvaSalvaRepository;
import br.edu.upe.passa.repository.ProvaRepository;
import br.edu.upe.passa.repository.UsuarioRepository;

import br.edu.upe.passa.service.ProvaSalvaService;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProvaSalvaServiceImpl implements ProvaSalvaService {

    private final ProvaSalvaRepository provaSalvaRepository;
    private final ProvaRepository provaRepository;
    private final UsuarioRepository UsuarioRepository;

    @Override
    @Transactional
    public ProvaSalva salvar(Integer idUsuario, Integer idProva) {

        if (provaSalvaRepository.existsByUsuarioIdAndProva_IdProva(idUsuario, idProva)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Esta prova já está salva"
            );
        }

        Usuario usuario = UsuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        Prova prova = provaRepository.findById(idProva)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prova não encontrada"
                ));

        ProvaSalva provaSalva = new ProvaSalva();

        ProvaSalvaId id = new ProvaSalvaId();
        id.setIdUsuario(idUsuario);
        id.setIdProva(idProva);

        provaSalva.setId(id);
        provaSalva.setUsuario(usuario);
        provaSalva.setProva(prova);
        provaSalva.setSalvoEm(LocalDateTime.now());

        return provaSalvaRepository.save(provaSalva);
    }

    @Override
    @Transactional(readOnly = true)
    public List<ProvaSalva> listarPorUsuario(Integer idUsuario) {

        if (!UsuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuário não encontrado"
            );
        }

        return provaSalvaRepository.findByUsuarioId(idUsuario);
    }

    @Override
    @Transactional
    public void remover(Integer idUsuario, Integer idProva) {

        ProvaSalvaId id = new ProvaSalvaId();
        id.setIdUsuario(idUsuario);
        id.setIdProva(idProva);

        ProvaSalva provaSalva = provaSalvaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prova salva não encontrada"
                ));

        provaSalvaRepository.delete(provaSalva);
    }
}