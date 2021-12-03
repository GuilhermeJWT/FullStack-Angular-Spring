package br.com.systemsgs.repository.lancamento;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	Page<ModelLancamentos> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable);

}
