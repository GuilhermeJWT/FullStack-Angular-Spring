package br.com.systemsgs.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.model.ModelCategoria;

@Repository
public interface CategoriaRepository extends JpaRepository<ModelCategoria, Long>{

	Optional<ModelCategoria> findByCodigo(Long codigo);

    ModelCategoria findById(Long codigo);
}
