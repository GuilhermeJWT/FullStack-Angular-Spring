package br.com.systemsgs.security;

import java.util.Collection;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.systemsgs.model.ModelUsuario;
import br.com.systemsgs.repository.UsuarioRepository;

@Service
public class ImplUserDetailsService implements UserDetailsService{

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Optional<ModelUsuario> usuarioOptinal = usuarioRepository.findByEmail(email);
		ModelUsuario usuario =  usuarioOptinal.orElseThrow(() -> new UsernameNotFoundException("Usuário ou Senha Incorretos!"));
		return new User(email, usuario.getSenha(), getPermissoes(usuario));
	}

	private Collection<? extends GrantedAuthority> getPermissoes(ModelUsuario usuario) {
		Set<SimpleGrantedAuthority> authorites = new HashSet<>();
		usuario.getPermissoes().forEach(p -> authorites.add(new SimpleGrantedAuthority(p.getDescricao().toUpperCase())));
		return authorites;
	}

}
