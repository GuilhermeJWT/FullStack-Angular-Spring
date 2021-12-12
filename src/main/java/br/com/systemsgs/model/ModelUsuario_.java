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

}

