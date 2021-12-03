package br.com.systemsgs.controller;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.filter.LancamentoFilter;
import br.com.systemsgs.service.LancamentosService;

@RestController
@RequestMapping(value = "/api/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentosService lancamentoService;
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<ModelLancamentos> salvarLancamento(@RequestBody @Valid ModelLancamentos modelLancamentos, HttpServletResponse response){
		ModelLancamentos lancamentoSalvo = lancamentoService.salvarLancamento(modelLancamentos, response);
		
		return ResponseEntity.status(HttpStatus.CREATED).body(lancamentoSalvo);
	}

	@GetMapping(value = "/listar")
	public Page<ModelLancamentos> pesquisar(LancamentoFilter lancamentoFilter, Pageable pageable){
		return lancamentoService.filtrarLancamento(lancamentoFilter, pageable);
	}

	@GetMapping(value = "/{codigo}")
	public ResponseEntity<ModelLancamentos> pesquisaPorCodigo(@PathVariable Long codigo) {
		ModelLancamentos lancamento = lancamentoService.pesquisaPorCodigo(codigo);
		return lancamento != null ? ResponseEntity.ok(lancamento) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/delete/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void removeLancamento(@PathVariable Long codigo) {
		lancamentoService.removeLancamento(codigo);
	}

}
