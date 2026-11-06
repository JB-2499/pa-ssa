package br.edu.upe.passa.controller;

import br.edu.upe.passa.entity.dto.UsuarioRequestDTO;
import br.edu.upe.passa.entity.dto.UsuarioResponseDTO;
import br.edu.upe.passa.entity.Usuario;
import br.edu.upe.passa.service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/usuarios")
@RequiredArgsConstructor
public class UsuarioController {

    private final UsuarioService usuarioService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public UsuarioResponseDTO criar(@Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuario = new Usuario();
        usuario.setNomeUsuario(dto.nomeUsuario());
        usuario.setEmail(dto.email());
        usuario.setSenhaHash(dto.senha()); // TODO Checkpoint 4: aplicar hash real (Spring Security)
        usuario.setDataNascimento(dto.dataNascimento());
        usuario.setEscola(dto.escola());
        usuario.setSerie(dto.serie());

        Usuario salvo = usuarioService.criar(usuario);
        return UsuarioResponseDTO.fromEntity(salvo);
    }

    @GetMapping
    public List<UsuarioResponseDTO> listarTodos() {
        return usuarioService.listarTodos().stream()
                .map(UsuarioResponseDTO::fromEntity)
                .toList();
    }

    @GetMapping("/{id}")
    public UsuarioResponseDTO buscarPorId(@PathVariable Integer id) {
        return UsuarioResponseDTO.fromEntity(usuarioService.buscarPorId(id));
    }

    @PutMapping("/{id}")
    public UsuarioResponseDTO atualizar(@PathVariable Integer id, @Valid @RequestBody UsuarioRequestDTO dto) {
        Usuario usuarioAtualizado = new Usuario();
        usuarioAtualizado.setNomeUsuario(dto.nomeUsuario());
        usuarioAtualizado.setEmail(dto.email());
        usuarioAtualizado.setSenhaHash(dto.senha());
        usuarioAtualizado.setDataNascimento(dto.dataNascimento());
        usuarioAtualizado.setEscola(dto.escola());
        usuarioAtualizado.setSerie(dto.serie());

        Usuario salvo = usuarioService.atualizar(id, usuarioAtualizado);
        return UsuarioResponseDTO.fromEntity(salvo);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Integer id) {
        usuarioService.deletar(id);
    }
}
