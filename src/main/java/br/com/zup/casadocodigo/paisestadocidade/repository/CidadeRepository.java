package br.com.zup.casadocodigo.paisestadocidade.repository;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import br.com.zup.casadocodigo.paisestadocidade.CidadeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CidadeRepository extends JpaRepository<CidadeEntity, Long> {

    Optional<CidadeEntity> findByNome(String nome);
}
