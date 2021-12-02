package br.com.systemsgs.repository.lancamento;

import java.util.List;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.filter.LancamentoFilter;

public interface LancamentoRepositoryQuery {
	
	public List<ModelLancamentos> filtrar(LancamentoFilter lancamentoFilter);

}
