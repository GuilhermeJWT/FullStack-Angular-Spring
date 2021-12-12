package br.com.systemsgs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModelUsuario.class)
public abstract class ModelUsuario_ {

	public static volatile SingularAttribute<ModelUsuario, String> senha;
	public static volatile ListAttribute<ModelUsuario, ModelPermissoes> permissoes;
	public static volatile SingularAttribute<ModelUsuario, Long> codigo;
	public static volatile SingularAttribute<ModelUsuario, String> nome;
	public static volatile SingularAttribute<ModelUsuario, String> email;

	public static final String SENHA = "senha";
	public static final String PERMISSOES = "permissoes";
	public static final String CODIGO = "codigo";
	public static final String NOME = "nome";
	public static final String EMAIL = "email";

}

