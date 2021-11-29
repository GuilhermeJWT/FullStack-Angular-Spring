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
		ModelPessoa pessoalSalva = buscarPessoaPeloCodigo(codigo);

		BeanUtils.copyProperties(modelPessoa, pessoalSalva, "codigo");
		return pessoaRepository.save(pessoalSalva);

	}

	public void atualizarPropriedadeAtivo(Long codigo, Boolean ativo) {
		ModelPessoa pessoalSalva = buscarPessoaPeloCodigo(codigo);
		pessoalSalva.setAtivo(ativo);
		pessoaRepository.save(pessoalSalva);
	}
	private ModelPessoa buscarPessoaPeloCodigo(Long codigo) {
		ModelPessoa pessoaSalva = pessoaRepository.findById(codigo).orElseThrow(() -> new RecursoNaoEncontradoException());
		
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

}
