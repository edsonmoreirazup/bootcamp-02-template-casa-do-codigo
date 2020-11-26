package br.com.zup.casadocodigo.paisestadocidade.repository;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.paisestadocidade.EstadoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface EstadoRepository extends JpaRepository<EstadoEntity, Long>  {

    Optional<EstadoEntity> findByNome(String nome);
}
