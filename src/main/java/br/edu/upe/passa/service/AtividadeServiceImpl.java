package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.AreaEntity;
import br.edu.upe.passa.entity.Atividade;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.AreaRepository;
import br.edu.upe.passa.repository.AtividadeRepository;
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
public class AtividadeServiceImpl implements AtividadeService {

    private final AtividadeRepository atividadeRepository;
    private final UsuarioRepository usuarioRepository;
    private final AreaRepository areaRepository;

    @Override
    @Transactional
    public Atividade registrar(Integer usuarioId, Integer areaId, String tipo, LocalDateTime ocorridaEm) {
        LocalDateTime momento = (ocorridaEm != null) ? ocorridaEm : LocalDateTime.now();

        if (momento.isAfter(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "A data da atividade não pode estar no futuro");
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        Area area = null;
        if (areaId != null) {
            area = areaRepository.findById(areaId)
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Área não encontrada"));
        }

        Atividade atividade = new Atividade();
        atividade.setUsuario(usuario);
        atividade.setArea(area);
        atividade.setTipo(tipo);
        atividade.setOcorridaEm(momento);

        return atividadeRepository.save(atividade);
    }

    @Override
    @Transactional(readOnly = true)
    public Atividade buscarPorId(Integer id) {
        return atividadeRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Atividade não encontrada"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Atividade> listarPorUsuario(Integer usuarioId) {
        if (!usuarioRepository.existsById(usuarioId)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado");
        }
        return atividadeRepository.findByUsuarioIdOrderByOcorridaEmDesc(usuarioId);
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
        Atividade atividade = buscarPorId(id);
        atividadeRepository.delete(atividade);
    }
}
