package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.ProvaSalva;
import br.edu.upe.passa.entity.ProvaSalvaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaSalvaRepository extends JpaRepository<ProvaSalva, ProvaSalvaId> {

    List<ProvaSalva> findByUsuarioId(Integer idUsuario);

    boolean existsByUsuarioIdAndProva_IdProva(Integer idUsuario, Integer idProva);
}