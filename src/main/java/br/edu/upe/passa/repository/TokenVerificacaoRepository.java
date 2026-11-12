package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.TokenVerificacao;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TokenVerificacaoRepository extends JpaRepository<TokenVerificacao, Integer> {

    Optional<TokenVerificacao> findByCodigo(String codigo);

    boolean existsByCodigo(String codigo);

    List<TokenVerificacao> findByUsuarioId(Integer usuarioId);
}
