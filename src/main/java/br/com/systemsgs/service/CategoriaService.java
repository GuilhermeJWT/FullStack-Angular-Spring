package br.com.systemsgs.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.systemsgs.model.ModelCategoria;
import br.com.systemsgs.repository.CategoriaRepository;

@Service
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;
	
	public ModelCategoria pesquisaCategoria(Long codigo){
		ModelCategoria categoria = categoriaRepository.findOne(codigo);
		return categoria;
	}
	
}
