package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.TokenVerificacao;

import java.util.List;

public interface TokenVerificacaoService {

    TokenVerificacao gerar(Integer usuarioId, String tipo, String emailNovo);

    TokenVerificacao validar(String codigo);

    List<TokenVerificacao> listarPorUsuario(Integer usuarioId);
}
