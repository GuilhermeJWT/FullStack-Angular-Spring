package br.com.systemsgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.model.ModelPessoa;

@Repository
public interface PessoaRepository extends JpaRepository<ModelPessoa, Long>{
	
}
