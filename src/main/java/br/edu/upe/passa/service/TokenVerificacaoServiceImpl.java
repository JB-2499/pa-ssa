package br.edu.upe.passa.service;

import br.edu.upe.passa.entity.TokenVerificacao;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.repository.TokenVerificacaoRepository;
import br.edu.upe.passa.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class TokenVerificacaoServiceImpl implements TokenVerificacaoService {

    // TODO: substituir pelos valores reais do CHECK definido no DER
    private static final List<String> TIPOS_VALIDOS =
            List.of("VERIFICACAO_EMAIL", "RESET_SENHA", "TROCA_EMAIL");

    private static final long VALIDADE_HORAS = 24;

    private final TokenVerificacaoRepository tokenVerificacaoRepository;
    private final UsuarioRepository usuarioRepository;

    @Override
    @Transactional
    public TokenVerificacao gerar(Integer usuarioId, String tipo, String emailNovo) {
        if (!TIPOS_VALIDOS.contains(tipo)) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "Tipo de token inválido. Valores aceitos: " + TIPOS_VALIDOS);
        }

        Usuario usuario = usuarioRepository.findById(usuarioId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        TokenVerificacao token = new TokenVerificacao();
        token.setUsuario(usuario);
        token.setTipo(tipo);
        token.setCodigo(UUID.randomUUID().toString());
        token.setEmailNovo(emailNovo);
        token.setExpiraEm(LocalDateTime.now().plusHours(VALIDADE_HORAS));

        return tokenVerificacaoRepository.save(token);
    }

    @Override
    @Transactional
    public TokenVerificacao validar(String codigo) {
        TokenVerificacao token = tokenVerificacaoRepository.findByCodigo(codigo)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Token não encontrado"));

        if (token.getUsadoEm() != null) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Este token já foi utilizado");
        }

        if (token.getExpiraEm().isBefore(LocalDateTime.now())) {
            throw new ResponseStatusException(HttpStatus.GONE, "Este token expirou");
        }

        token.setUsadoEm(LocalDateTime.now());
        return tokenVerificacaoRepository.save(token);
    }

    @Override
    @Transactional(readOnly = true)
    public List<TokenVerificacao> listarPorUsuario(Integer usuarioId) {
        return tokenVerificacaoRepository.findByUsuarioId(usuarioId);
    }
}
