package br.com.systemsgs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.event.RecursoCriadoEvent;
import br.com.systemsgs.model.ModelPessoa;
import br.com.systemsgs.repository.PessoaRepository;
import br.com.systemsgs.service.PessoaService;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaController {
	
	@Autowired
	private PessoaService pessoaService;
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;

	@GetMapping(value = "/listar")
	public List<ModelPessoa> listar(){
		return pessoaRepository.findAll();
	}
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<ModelPessoa> salvar(@Valid @RequestBody ModelPessoa modelPessoa, HttpServletResponse response){
		ModelPessoa pessoaSalva = pessoaRepository.save(modelPessoa);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, pessoaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(pessoaSalva);
	}
	
	@GetMapping(value = "/{codigo}")
	 public ResponseEntity<ModelPessoa> recuperaPorCodigo(@PathVariable Long codigo) {
		ModelPessoa pessoaPesquisada = pessoaService.buscarPessoaPeloCodigo(codigo);
		return pessoaPesquisada != null ? ResponseEntity.ok(pessoaPesquisada) : ResponseEntity.notFound().build();
	}
	
	@DeleteMapping(value = "/delete/{codigo}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Long codigo) {
		 pessoaRepository.delete(codigo);
	}
	
	@PutMapping(value = "/editar/{codigo}")
	public ResponseEntity<ModelPessoa> atualizar(@PathVariable Long codigo, @Valid @RequestBody ModelPessoa modelPessoa){
		ModelPessoa pessoaSalva = pessoaService.atualizar(codigo, modelPessoa);

		return ResponseEntity.ok(pessoaSalva);
	}
	
	@PutMapping(value = "/editar/{codigo}/ativo")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarPropriedadeAtivo(@PathVariable Long codigo, @RequestBody Boolean ativo) {
		pessoaService.atualizarPropriedadeAtivo(codigo, ativo);
	}
	
}
