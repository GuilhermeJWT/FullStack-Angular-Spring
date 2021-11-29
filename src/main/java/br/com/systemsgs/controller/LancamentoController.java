package br.com.systemsgs.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.repository.LancamentoRepository;
import br.com.systemsgs.service.LancamentosService;

@RestController()
@RequestMapping(value = "/api/lancamentos")
public class LancamentoController {

	@Autowired
	private LancamentosService lancamentoService;

	@GetMapping(value = "/listar")
	public List<ModelLancamentos> listar(){
		return lancamentoService.listaLancamentos();
	}

	@GetMapping(value = "/{codigo}")
	public ModelLancamentos pesquisaPorCodigo(@PathVariable Long codigo) {
		return lancamentoService.pesquisaPorCodigo(codigo);
	}

}
