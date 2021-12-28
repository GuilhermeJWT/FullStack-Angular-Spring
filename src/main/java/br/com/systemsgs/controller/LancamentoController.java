package br.com.systemsgs.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.filter.LancamentoFilter;
import br.com.systemsgs.repository.projection.ResumoLancamento;
import br.com.systemsgs.service.LancamentosService;

@RestController
@RequestMapping(value = "/api/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentosService lancamentoService;
	
	@PostMapping(value = "/salvar")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO') and #oauth2.hasScope('write')")
	public ResponseEntity<ModelLancamentos> salvarLancamento(@RequestBody @Valid ModelLancamentos modelLancamentos, HttpServletResponse response){
		ModelLancamentos lancamentoSalvo = lancamentoService.salvarLancamento(modelLancamentos, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}

	@GetMapping(value = "/listar")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ModelLancamentos> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoService.filtrarLancamento(lancamentoFilter, pageable);
	}
	
	@GetMapping(value = "/listar", params = "resumo")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoService.resumir(lancamentoFilter, pageable);
	}

	@GetMapping(value = "/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_PESQUISAR_LANCAMENTO') and #oauth2.hasScope('read')")
	public ResponseEntity<ModelLancamentos> pesquisaPorCodigo(@PathVariable Long codigo) {
		ModelLancamentos lancamento = lancamentoService.pesquisaPorCodigo(codigo);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/delete/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	@PreAuthorize("hasAuthority('ROLE_REMOVER_LANCAMENTO') and #oauth2.hasScope('write')")
	public void removeLancamento(@PathVariable Long codigo) {
		lancamentoService.removeLancamento(codigo);
	}
	
	@PutMapping(value = "/alterar/{codigo}")
	@PreAuthorize("hasAuthority('ROLE_CADASTRAR_LANCAMENTO')")
	public ResponseEntity<ModelLancamentos> atualizar(@PathVariable Long codigo, @RequestBody @Valid ModelLancamentos modelLancamentos){
		try {
			ModelLancamentos lancamentoSalvo = lancamentoService.atualizar(codigo, modelLancamentos);
			return ResponseEntity.ok(lancamentoSalvo);
		} catch (IllegalArgumentException e) {
			return ResponseEntity.notFound().build();
		}
	}
	

}
