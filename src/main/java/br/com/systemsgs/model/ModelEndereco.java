package br.com.systemsgs.model;

import javax.persistence.Embeddable;
import javax.validation.constraints.Size;

@Embeddable
public class ModelEndereco {
	
	@Size(max = 30, message = "Logradouro deve conter no Máximo 30 Caracteres!!!")
	private String logradouro;
	
	@Size(max = 30, message = "Número deve conter no Máximo 30 Caracteres!!!")
	private String numero;
	
	@Size(max = 30, message = "Complemento deve conter no Máximo 30 Caracteres!!!")
	private String complemento;
	
	@Size(max = 30, message = "Bairro deve conter no Máximo 30 Caracteres!!!")
	private String bairro;
	
	@Size(max = 10, message = "Cep deve conter no Máximo 10 Caracteres!!!")
	private String cep;
	
	@Size(max = 30, message = "Cidade deve conter no Máximo 30 Caracteres!!!")
	private String cidade;
	
	@Size( max = 30, message = "Estado deve conter no Máximo 30 Caracteres!!!")
	private String estado;
	
	public String getLogradouro() {
		return logradouro;
	}

	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	public String getNumero() {
		return numero;
	}

	public void setNumero(String numero) {
		this.numero = numero;
	}

	public String getComplemento() {
		return complemento;
	}

	public void setComplemento(String complemento) {
		this.complemento = complemento;
	}

	public String getBairro() {
		return bairro;
	}

	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	public String getCep() {
		return cep;
	}

	public void setCep(String cep) {
		this.cep = cep;
	}

	public String getCidade() {
		return cidade;
	}

	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

}
