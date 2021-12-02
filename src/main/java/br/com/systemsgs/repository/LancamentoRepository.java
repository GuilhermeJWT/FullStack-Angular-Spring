package br.com.systemsgs.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.lancamento.LancamentoRepositoryQuery;

@Repository
public interface LancamentoRepository extends JpaRepository<ModelLancamentos, Long>, LancamentoRepositoryQuery{

}
