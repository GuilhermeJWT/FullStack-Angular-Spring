package br.com.systemsgs.controller;

import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.event.RecursoCriadoEvent;
import br.com.systemsgs.exception.RecursoNaoEncontradoException;
import br.com.systemsgs.model.ModelPessoa;
import br.com.systemsgs.repository.PessoaRepository;

@RestController
@RequestMapping(value = "/api/pessoas")
public class PessoaController {
	
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
	 public ModelPessoa recuperaPorCodigo(@PathVariable Long codigo) {
		return pessoaRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
	}
	
}
