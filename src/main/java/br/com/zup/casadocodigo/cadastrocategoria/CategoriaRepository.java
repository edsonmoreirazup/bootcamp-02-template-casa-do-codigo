package br.com.zup.casadocodigo.cadastrocategoria;

import br.com.zup.casadocodigo.novoautor.AutorEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoriaRepository extends JpaRepository<CategoriaEntity, Long> {

    Optional<CategoriaEntity> findByNome(String nome);
}
