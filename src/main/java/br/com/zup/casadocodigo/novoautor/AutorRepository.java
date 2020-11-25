package br.com.zup.casadocodigo.novoautor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AutorRepository extends JpaRepository<AutorEntity, Long> {

    Optional<AutorEntity> findByEmail(String email);

    Optional<AutorEntity> findByNomeAndEmail(String nome, String email);
}
