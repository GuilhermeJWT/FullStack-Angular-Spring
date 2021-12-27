package br.com.systemsgs.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.model.ModelPessoa;

@Repository
public interface PessoaRepository extends JpaRepository<ModelPessoa, Long>{
	
	public Page<ModelPessoa> findByNomeContaining(String nome, Pageable pageable);
	
}
