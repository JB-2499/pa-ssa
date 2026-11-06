package br.edu.upe.passa.service.implementService;

import br.edu.upe.passa.entity.ProvaSalvaEntity;
import br.edu.upe.passa.entity.ProvaSalvaId;
import br.edu.upe.passa.entity.ProvaEntity;
import br.edu.upe.passa.entity.UsuarioEntity;

import br.edu.upe.passa.repository.ProvaSalvaRepository;
import br.edu.upe.passa.repository.ProvaRepository;
import br.edu.upe.passa.repository.usuarioRepository;

import br.edu.upe.passa.service.interfaceService.ProvaSalvaService;

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
    private final usuarioRepository UsuarioRepository;

    @Override
    @Transactional
    public ProvaSalvaEntity salvar(Long idUsuario, Long idProva) {

        if (provaSalvaRepository
                .existsByUsuario_IdUsuarioAndProva_IdProva(idUsuario, idProva)) {

            throw new ResponseStatusException(
                    HttpStatus.CONFLICT,
                    "Esta prova já está salva"
            );
        }

        UsuarioEntity usuario = usuarioRepository.findById(idUsuario)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Usuário não encontrado"
                ));

        ProvaEntity prova = provaRepository.findById(idProva)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prova não encontrada"
                ));

        ProvaSalvaEntity provaSalva = new ProvaSalvaEntity();

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
    public List<ProvaSalvaEntity> listarPorUsuario(Long idUsuario) {

        if (!usuarioRepository.existsById(idUsuario)) {
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND,
                    "Usuário não encontrado"
            );
        }

        return provaSalvaRepository.findByUsuario_IdUsuario(idUsuario);
    }

    @Override
    @Transactional
    public void remover(Long idUsuario, Long idProva) {

        ProvaSalvaId id = new ProvaSalvaId();
        id.setIdUsuario(idUsuario);
        id.setIdProva(idProva);

        ProvaSalvaEntity provaSalva = provaSalvaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND,
                        "Prova salva não encontrada"
                ));

        provaSalvaRepository.delete(provaSalva);
    }
}