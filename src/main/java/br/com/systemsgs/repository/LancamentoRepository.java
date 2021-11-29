package br.com.systemsgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.model.ModelLancamentos;

@Repository
public interface LancamentoRepository extends JpaRepository<ModelLancamentos, Long>{

}
