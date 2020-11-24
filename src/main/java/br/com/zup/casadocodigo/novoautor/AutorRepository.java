package br.com.zup.casadocodigo.novoautor;

import br.com.zup.casadocodigo.cadastrocategoria.CategoriaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

}
