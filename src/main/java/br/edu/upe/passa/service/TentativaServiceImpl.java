package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Prova;
import br.edu.upe.passa.entity.Simulado;
import br.edu.upe.passa.entity.Tentativa;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.ProvaRepository;
import br.edu.upe.passa.repository.SimuladoRepository;
import br.edu.upe.passa.repository.TentativaRepository;
import br.edu.upe.passa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TentativaServiceImpl implements TentativaService {

    private final TentativaRepository tentativaRepository;
    private final UsuarioRepository usuarioRepository;
    private final SimuladoRepository simuladoRepository;
    private final ProvaRepository provaRepository;

    @Override
    @Transactional
    public Tentativa iniciar(Integer usuarioId, Integer simuladoId, Integer provaId) {
        if ((simuladoId == null) == (provaId == null)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Informe exatamente um entre simuladoId e provaId");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Tentativa tentativa = new Tentativa();
        tentativa.setUsuario(usuario);

        if (simuladoId != null) {
            Simulado simulado = simuladoRepository.findById(simuladoId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulado não encontrado"));
            if (!simulado.getUsuario().getId().equals(usuarioId)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN,
                        "Este simulado pertence a outro usuário");
            }
            tentativa.setSimulado(simulado);
        } else {
            Prova prova = provaRepository.findById(provaId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Prova não encontrada"));
            tentativa.setProva(prova);
        }

        return tentativaRepository.save(tentativa);
    }

    @Override
    @Transactional(readOnly = true)
    public Tentativa buscarPorId(Integer id) {
        return tentativaRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Tentativa não encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Tentativa> listarPorUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return tentativaRepository.findByUsuarioIdOrderByIniciadaEmDesc(usuarioId);
    }

    @Override
    @Transactional
    public Tentativa finalizar(Integer id) {
        Tentativa tentativa = buscarPorId(id);

        if (tentativa.getFinalizadaEm() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Esta tentativa já foi finalizada");
        }

        tentativa.setFinalizadaEm(LocalDateTime.now());
        return tentativaRepository.save(tentativa);
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
        Tentativa tentativa = buscarPorId(id);
        tentativaRepository.delete(tentativa);
    }
}
