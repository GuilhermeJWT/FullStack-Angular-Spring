package br.com.systemsgs.security;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class GeradorSenhaEncode {
	
	public static void main(String[] args) {
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		System.out.println(encoder.encode("admin"));
	}

}
