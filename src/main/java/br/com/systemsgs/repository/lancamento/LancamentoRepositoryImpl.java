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

import org.springframework.util.StringUtils;

import br.com.systemsgs.model.ModelLancamentos;
import br.com.systemsgs.model.ModelLancamentos_;
import br.com.systemsgs.repository.filter.LancamentoFilter;

public class LancamentoRepositoryImpl implements LancamentoRepositoryQuery{
	
	@PersistenceContext
	private EntityManager manager;

	@Override
	public List<ModelLancamentos> filtrar(LancamentoFilter lancamentoFilter) {
		CriteriaBuilder builder = manager.getCriteriaBuilder();
		CriteriaQuery<ModelLancamentos> criteria = builder.createQuery(ModelLancamentos.class);
		Root<ModelLancamentos> root = criteria.from(ModelLancamentos.class);
		
		Predicate[] predicates = criarRestricoes(lancamentoFilter, builder, root);
		criteria.where(predicates);
		
		TypedQuery<ModelLancamentos> query = manager.createQuery(criteria);
		return query.getResultList();
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

}
