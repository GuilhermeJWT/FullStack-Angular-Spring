package br.com.systemsgs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

import br.com.systemsgs.event.RecursoCriadoEvent;
import br.com.systemsgs.exception.PessoaInexistenteOuInativaException;
import br.com.systemsgs.exception.RecursoNaoEncontradoException;
import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.model.ModelPessoa;
import br.com.systemsgs.repository.LancamentoRepository;
import br.com.systemsgs.repository.PessoaRepository;
import br.com.systemsgs.repository.filter.LancamentoFilter;

@Service
public class LancamentosService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Transactional
	public ModelLancamentos salvarLancamento(ModelLancamentos modelLancamentos, HttpServletResponse response){
		ModelLancamentos lancamentoSalvo = lancamentoRepository.save(modelLancamentos);
		ModelPessoa modelPessoa = pessoaRepository.findById(modelLancamentos.getPessoa().getCodigo()).orElseThrow(() -> new RecursoNaoEncontradoException());
		
		if(modelLancamentos == null || modelPessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		
		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	public List<ModelLancamentos> listaLancamentos(){
		return lancamentoRepository.findAll();
	}
	
	public ModelLancamentos pesquisaPorCodigo(Long codigo) {
		return lancamentoRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
	}

	public List<ModelLancamentos> filtrarLancamento(LancamentoFilter lancamentoFilter) {
		return lancamentoRepository.filtrar(lancamentoFilter);
	}
	
}
