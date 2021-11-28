package br.com.systemsgs.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import br.com.systemsgs.exception.RecursoNaoEncontradoException;
import br.com.systemsgs.model.ModelPessoa;
import br.com.systemsgs.repository.PessoaRepository;

@Service
public class PessoaService {
	
	@Autowired
	private PessoaRepository pessoaRepository;
	
	public ModelPessoa atualizar(Long codigo, ModelPessoa modelPessoa) {
		
		ModelPessoa pessoalSalva = pessoaRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
		
		if(pessoalSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		
		BeanUtils.copyProperties(modelPessoa, pessoalSalva, "codigo");
		return pessoaRepository.save(pessoalSalva);
		
	}

}
