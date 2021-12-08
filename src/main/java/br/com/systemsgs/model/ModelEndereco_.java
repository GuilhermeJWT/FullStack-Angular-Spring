package br.com.systemsgs.model;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ModelEndereco.class)
public abstract class ModelEndereco_ {

	public static volatile SingularAttribute<ModelEndereco, String> cidade;
	public static volatile SingularAttribute<ModelEndereco, String> estado;
	public static volatile SingularAttribute<ModelEndereco, String> complemento;
	public static volatile SingularAttribute<ModelEndereco, String> numero;
	public static volatile SingularAttribute<ModelEndereco, String> logradouro;
	public static volatile SingularAttribute<ModelEndereco, String> bairro;
	public static volatile SingularAttribute<ModelEndereco, String> cep;

	public static final String CIDADE = "cidade";
	public static final String ESTADO = "estado";
	public static final String COMPLEMENTO = "complemento";
	public static final String NUMERO = "numero";
	public static final String LOGRADOURO = "logradouro";
	public static final String BAIRRO = "bairro";
	public static final String CEP = "cep";

}

