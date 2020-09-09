package com.algaworks.cobranca.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;

import com.algaworks.cobranca.model.Usuario;

public class UsuarioLogado extends User{

	@Autowired
	private Usuario usuario;
	
	 public UsuarioLogado(Usuario usuario, Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getNome(), usuario.getSenha(),authorities);
		this.usuario = usuario;
	}

	 public Long getId() {
		 return usuario.getId();
	 }
	
	 public String getNome() {
		 return usuario.getNome();
	 }

		

	
	

}
