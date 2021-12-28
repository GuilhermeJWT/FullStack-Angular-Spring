package br.com.systemsgs.service;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.systemsgs.event.RecursoCriadoEvent;
import br.com.systemsgs.exception.PessoaInexistenteOuInativaException;
import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.model.ModelPessoa;
import br.com.systemsgs.repository.LancamentoRepository;
import br.com.systemsgs.repository.PessoaRepository;
import br.com.systemsgs.repository.filter.LancamentoFilter;
import br.com.systemsgs.repository.projection.ResumoLancamento;

@Service
public class LancamentosService {

	@Autowired
	private LancamentoRepository lancamentoRepository;

	@Autowired
	private ApplicationEventPublisher publisher;

	@Autowired
	private PessoaRepository pessoaRepository;

	@Transactional
	public ModelLancamentos salvarLancamento(ModelLancamentos modelLancamentos, HttpServletResponse response) {
		ModelLancamentos lancamentoSalvo = lancamentoRepository.save(modelLancamentos);
		ModelPessoa modelPessoa = pessoaRepository.findOne(modelLancamentos.getPessoa().getCodigo());

		if (modelLancamentos == null || modelPessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}

		publisher.publishEvent(new RecursoCriadoEvent(this, response, lancamentoSalvo.getCodigo()));

		return lancamentoRepository.save(lancamentoSalvo);
	}

	public List<ModelLancamentos> listaLancamentos() {
		return lancamentoRepository.findAll();
	}

	public ModelLancamentos pesquisaPorCodigo(Long codigo) {
		ModelLancamentos lancamento = lancamentoRepository.findOne(codigo);
		return lancamento;
	}

	public Page<ModelLancamentos> filtrarLancamento(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.filtrar(lancamentoFilter, pageable);
	}

	public void removeLancamento(Long codigo) {
		lancamentoRepository.delete(codigo);
	}

	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		return lancamentoRepository.resumir(lancamentoFilter, pageable);
	}

	public ModelLancamentos atualizar(Long codigo, ModelLancamentos modelLancamentos) {
		ModelLancamentos lancamentoSalvo = buscarLancamentoExistente(codigo);
		if (!modelLancamentos.getPessoa().equals(lancamentoSalvo.getPessoa())) {
			validarPessoa(modelLancamentos);
		}

		BeanUtils.copyProperties(modelLancamentos, lancamentoSalvo, "codigo");

		return lancamentoRepository.save(lancamentoSalvo);
	}
	
	private void validarPessoa(ModelLancamentos lancamento) {
		ModelPessoa modelPessoa = null;
		if (lancamento.getPessoa().getCodigo() != null) {
			modelPessoa = pessoaRepository.findOne(lancamento.getPessoa().getCodigo());
		}

		if (modelPessoa == null || modelPessoa.isInativo()) {
			throw new PessoaInexistenteOuInativaException();
		}
	}
	
	private ModelLancamentos buscarLancamentoExistente(Long codigo) {
		ModelLancamentos lancamentoSalvo = lancamentoRepository.findOne(codigo);
		if (lancamentoSalvo == null) {
			throw new IllegalArgumentException();
		}
		return lancamentoSalvo;
	}

}
