package br.com.zup.casadocodigo.paisestadocidade.repository;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.paisestadocidade.PaisEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaisRepository extends JpaRepository<PaisEntity, Long> {

    Optional<PaisEntity> findByNome(String nome);
}
