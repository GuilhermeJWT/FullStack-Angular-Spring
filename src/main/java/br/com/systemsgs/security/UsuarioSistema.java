package br.com.systemsgs.security;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import br.com.systemsgs.model.ModelUsuario;

public class UsuarioSistema extends User{
	
	private static final long serialVersionUID = 1L;

	private ModelUsuario usuario;

	public UsuarioSistema(ModelUsuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getEmail(), usuario.getSenha(), authorities);
		this.usuario = usuario;
	}

	public ModelUsuario getUsuario() {
		return usuario;
	}

}
