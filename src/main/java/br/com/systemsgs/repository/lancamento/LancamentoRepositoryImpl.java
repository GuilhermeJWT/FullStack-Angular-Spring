package br.com.systemsgs.repository.lancamento;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.util.StringUtils;

import br.com.systemsgs.model.ModelCategoria_;
import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.model.ModelLancamentos_;
import br.com.systemsgs.model.ModelPessoa_;
import br.com.systemsgs.repository.filter.LancamentoFilter;
import br.com.systemsgs.repository.projection.ResumoLancamento;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public Page<ModelLancamentos> filtrar(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ModelLancamentos> criteria = builder.createQuery(ModelLancamentos.class);
		Root<ModelLancamentos> root = criteria.from(ModelLancamentos.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ModelLancamentos> query = manager.createQuery(criteria);
		adicionarRestricoesDaPaginacao(query, pageable);
		
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}
	
	@Override
	public Page<ResumoLancamento> resumir(LancamentoFilter lancamentoFilter, Pageable pageable) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ResumoLancamento> criteria = builder.createQuery(ResumoLancamento.class);
		Root<ModelLancamentos> root = criteria.from(ModelLancamentos.class);
		
		criteria.select(builder.construct(ResumoLancamento.class
				, root.get(ModelLancamentos_.codigo), root.get(ModelLancamentos_.descricao)
				, root.get(ModelLancamentos_.dataVencimento), root.get(ModelLancamentos_.dataPagamento)
				, root.get(ModelLancamentos_.valor), root.get(ModelLancamentos_.tipo)
				, root.get(ModelLancamentos_.categoria).get(ModelCategoria_.nome)
				, root.get(ModelLancamentos_.pessoa).get(ModelPessoa_.nome)));
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ResumoLancamento> query = manager.createQuery(criteria);
		adicionarRestricoesDaPaginacao(query, pageable);
		
		return new PageImpl<>(query.getResultList(), pageable, total(lancamentoFilter));
	}

	@SuppressWarnings("deprecation")
	private Predicate[] criarRestricoes(LancamentoFilter lancamentoFilter, CriteriaBuilder builder, Root<ModelLancamentos> root) {
		
		List<Predicate> predicates = new ArrayList<>();
		
		if(!StringUtils.isEmpty(lancamentoFilter.getDescricao())) {
			predicates.add(builder.like(builder.lower(root.get(ModelLancamentos_.descricao)), "%" + lancamentoFilter.getDescricao().toLowerCase() + "%"));
		}
		
		if(lancamentoFilter.getDataVencimentoDe() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(ModelLancamentos_.dataVencimento), lancamentoFilter.getDataVencimentoDe()));
		}
		
		if(lancamentoFilter.getDataVencimentoAte() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get(ModelLancamentos_.dataVencimento), lancamentoFilter.getDataVencimentoAte()));
		}
			
		return predicates.toArray(new Predicate[predicates.size()]);
	}
	
	private void adicionarRestricoesDaPaginacao(TypedQuery<?> query, Pageable pageable) {
		int paginaAtual = pageable.getPageNumber();
		int totalRegistroPorPagina = pageable.getPageSize();
		int primeiroRegistroDaPagina = paginaAtual * totalRegistroPorPagina;
		
		query.setFirstResult(primeiroRegistroDaPagina);
		query.setMaxResults(totalRegistroPorPagina);
	}
	
	private Long total(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<Long> criteria = builder.createQuery(Long.class);
		Root<ModelLancamentos> root = criteria.from(ModelLancamentos.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		criteria.select(builder.count(root));
		
		return manager.createQuery(criteria).getSingleResult();
	}

}
