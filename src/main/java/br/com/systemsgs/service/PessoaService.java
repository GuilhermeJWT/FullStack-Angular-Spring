package br.com.systemsgs.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

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
	
	public ModelPessoa buscarPessoaPeloCodigo(Long codigo) {
		ModelPessoa pessoaSalva = pessoaRepository.findOne(codigo);
		if (pessoaSalva == null) {
			throw new EmptyResultDataAccessException(1);
		}
		return pessoaSalva;
	}

	public ModelPessoa pesquisaPorId(Long codigo) {
		ModelPessoa pessoa = pessoaRepository.findOne(codigo);
		return pessoa;
	}

	public Page<ModelPessoa> pesquisaPorNome(String nome, Pageable pageable) {
		return pessoaRepository.findByNomeContaining(nome, pageable);
	}

}
