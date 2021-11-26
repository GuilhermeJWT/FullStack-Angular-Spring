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
import br.com.systemsgs.model.ModelCategoria;
import br.com.systemsgs.repository.CategoriaRepository;

@RestController
@RequestMapping(value = "/api/categorias")
public class CategoriaController {
	
	@Autowired
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private ApplicationEventPublisher publisher;
	
	@GetMapping(value = "/listar")
	public List<ModelCategoria> listar(){
		return categoriaRepository.findAll();
	}
	
	@PostMapping(value = "/salvar")
	public ResponseEntity<ModelCategoria> salvar(@Valid @RequestBody ModelCategoria modelCategoria, HttpServletResponse response) {
		ModelCategoria categoriaSalva = categoriaRepository.save(modelCategoria);
		
		publisher.publishEvent(new RecursoCriadoEvent(this, response, categoriaSalva.getCodigo()));
		
		return ResponseEntity.status(HttpStatus.CREATED).body(categoriaSalva);
	}

	 @GetMapping("/{codigo}")
	    public ModelCategoria recuperaPorCodigo(@PathVariable Long codigo){
	        return categoriaRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
	    }
	
	/*
	@GetMapping("/{codigo}")
	public ResponseEntity<ModelCategoria> buscarPeloCodigo(@PathVariable Long codigo) {
		ModelCategoria categoria = categoriaRepository.findById(codigo);
		 return categoria != null ? ResponseEntity.ok(categoria) : ResponseEntity.notFound().build();
	}
	*/

}
