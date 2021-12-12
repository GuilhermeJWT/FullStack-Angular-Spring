package br.com.systemsgs.model;

import br.com.systemsgs.enums.TipoLancamento;
import java.math.BigDecimal;
import java.time.LocalDate;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModelLancamentos.class)
public abstract class ModelLancamentos_ {

	public static volatile SingularAttribute<ModelLancamentos, Long> codigo;
	public static volatile SingularAttribute<ModelLancamentos, String> observacao;
	public static volatile SingularAttribute<ModelLancamentos, TipoLancamento> tipo;
	public static volatile SingularAttribute<ModelLancamentos, LocalDate> dataPagamento;
	public static volatile SingularAttribute<ModelLancamentos, ModelPessoa> pessoa;
	public static volatile SingularAttribute<ModelLancamentos, LocalDate> dataVencimento;
	public static volatile SingularAttribute<ModelLancamentos, ModelCategoria> categoria;
	public static volatile SingularAttribute<ModelLancamentos, BigDecimal> valor;
	public static volatile SingularAttribute<ModelLancamentos, String> descricao;

}

