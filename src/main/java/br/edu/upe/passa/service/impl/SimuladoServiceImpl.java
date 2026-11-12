package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Simulado;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.SimuladoRepository;
import br.edu.upe.passa.repository.UsuarioRepository;
import br.edu.upe.passa.service.SimuladoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SimuladoServiceImpl implements SimuladoService {

    private final SimuladoRepository simuladoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Simulado criar(Integer usuarioId, String nome, Integer tempoLimiteMinutos) {
        validarTempoLimite(tempoLimiteMinutos);

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Simulado simulado = new Simulado();
        simulado.setUsuario(usuario);
        simulado.setNome(nome);
        simulado.setTempoLimiteMinutos(tempoLimiteMinutos);

        return simuladoRepository.save(simulado);
    }

    @Override
    @Transactional(readOnly = true)
    public Simulado buscarPorId(Integer id) {
        return simuladoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Simulado não encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Simulado> listarPorUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return simuladoRepository.findByUsuarioId(usuarioId);
    }

    @Override
    @Transactional
    public Simulado atualizar(Integer id, String nome, Integer tempoLimiteMinutos) {
        validarTempoLimite(tempoLimiteMinutos);

        Simulado simulado = buscarPorId(id);
        simulado.setNome(nome);
        simulado.setTempoLimiteMinutos(tempoLimiteMinutos);

        return simuladoRepository.save(simulado);
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
        Simulado simulado = buscarPorId(id);
        simuladoRepository.delete(simulado);
    }

    private void validarTempoLimite(Integer tempoLimiteMinutos) {
        if (tempoLimiteMinutos != null && tempoLimiteMinutos <= 0) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "O tempo limite deve ser maior que zero");
        }
    }
}
