package br.com.systemsgs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModelPessoa.class)
public abstract class ModelPessoa_ {

	public static volatile SingularAttribute<ModelPessoa, Long> codigo;
	public static volatile SingularAttribute<ModelPessoa, Boolean> ativo;
	public static volatile SingularAttribute<ModelPessoa, ModelEndereco> endereco;
	public static volatile SingularAttribute<ModelPessoa, String> nome;

	public static final String CODIGO = "codigo";
	public static final String ATIVO = "ativo";
	public static final String ENDERECO = "endereco";
	public static final String NOME = "nome";

}

