package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.Usuario;

import java.util.List;

public interface UsuarioService {

    Usuario criar(Usuario usuario);

    Usuario buscarPorId(Integer id);

    List<Usuario> listarTodos();

    Usuario atualizar(Integer id, Usuario usuarioAtualizado);

    void deletar(Integer id);
}