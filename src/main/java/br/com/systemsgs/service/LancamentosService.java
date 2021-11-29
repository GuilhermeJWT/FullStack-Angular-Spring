package br.com.systemsgs.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.exception.RecursoNaoEncontradoException;
import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.LancamentoRepository;

@Service
public class LancamentosService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	public List<ModelLancamentos> listaLancamentos(){
		return lancamentoRepository.findAll();
	}
	
	public ModelLancamentos pesquisaPorCodigo(Long codigo) {
		return lancamentoRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
	}
	
}
