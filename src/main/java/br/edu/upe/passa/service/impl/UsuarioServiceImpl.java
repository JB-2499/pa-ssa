package br.edu.upe.passa.service.impl;

import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.UsuarioRepository;
import br.edu.upe.passa.service.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UsuarioServiceImpl implements UsuarioService {

    private static final List<String> SERIES_VALIDAS =
            List.of("9º Ano", "1ª Série", "2ª Série", "3ª Série");

    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public Usuario criar(Usuario usuario) {
        validarSerie(usuario.getSerie());

        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário cadastrado com este email");
        }
        if (usuarioRepository.existsByNomeUsuario(usuario.getNomeUsuario())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este nome de usuário já está em uso");
        }

        usuario.setVerificado(false);

        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario buscarPorId(Integer id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));
    }

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> listarTodos() {
        return usuarioRepository.findAll();
    }

    @Override
    @Transactional
    public Usuario atualizar(Integer id, Usuario usuarioAtualizado) {
        Usuario usuarioExistente = buscarPorId(id);

        validarSerie(usuarioAtualizado.getSerie());

        if (!usuarioExistente.getEmail().equals(usuarioAtualizado.getEmail())
                && usuarioRepository.existsByEmail(usuarioAtualizado.getEmail())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Já existe um usuário cadastrado com este email");
        }
        if (!usuarioExistente.getNomeUsuario().equals(usuarioAtualizado.getNomeUsuario())
                && usuarioRepository.existsByNomeUsuario(usuarioAtualizado.getNomeUsuario())) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este nome de usuário já está em uso");
        }

        usuarioExistente.setNomeUsuario(usuarioAtualizado.getNomeUsuario());
        usuarioExistente.setEmail(usuarioAtualizado.getEmail());
        usuarioExistente.setDataNascimento(usuarioAtualizado.getDataNascimento());
        usuarioExistente.setEscola(usuarioAtualizado.getEscola());
        usuarioExistente.setSerie(usuarioAtualizado.getSerie());

        return usuarioRepository.save(usuarioExistente);
    }

    @Override
    @Transactional
    public void deletar(Integer id) {
        Usuario usuario = buscarPorId(id);
        usuarioRepository.delete(usuario);
    }

    private void validarSerie(String serie) {
        if (!SERIES_VALIDAS.contains(serie)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Série inválida. Valores aceitos: " + SERIES_VALIDAS);
        }
    }
}