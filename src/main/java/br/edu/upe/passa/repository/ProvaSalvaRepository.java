package br.edu.upe.passa.repository;

import br.edu.upe.passa.entity.ProvaSalvaEntity;
import br.edu.upe.passa.entity.ProvaSalvaId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProvaSalvaRepository extends JpaRepository<ProvaSalvaEntity, ProvaSalvaId> {

    List<ProvaSalvaEntity> findByUsuarioId(Integer idUsuario);

    boolean existsByUsuarioIdAndProva_IdProva(Integer idUsuario, Integer idProva);
}