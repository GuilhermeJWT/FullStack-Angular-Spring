package br.com.systemsgs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import br.com.systemsgs.event.RecursoCriadoEvent;
import br.com.systemsgs.exception.RecursoNaoEncontradoException;
import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.LancamentoRepository;

@Service
public class LancamentosService {

	@Autowired
	private LancamentoRepository lancamentoRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@Transactional
	public ModelLancamentos salvarLancamento(ModelLancamentos modelLancamentos, HttpServletResponse response){
		ModelLancamentos lancamentoSalvo = lancamentoRepository.save(modelLancamentos);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));
		
		return lancamentoSalvo;
	}
	
	public List<ModelLancamentos> listaLancamentos(){
		return lancamentoRepository.findAll();
	}
	
	public ModelLancamentos pesquisaPorCodigo(Long codigo) {
		return lancamentoRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
	}
	
}
