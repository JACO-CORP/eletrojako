package com.eletrojako.security.services;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.eletrojako.models.Usuario;
import com.fasterxml.jackson.annotation.JsonIgnore;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String email;
	@JsonIgnore
	private String senha;
	
	private Collection<? extends GrantedAuthority> acessos;
	
	public UserDetailsImpl(Long id, String email, String senha, Collection<? extends GrantedAuthority> acessos) {
		this.id = id;
		this.email = email;
		this.senha = senha;
		this.acessos = acessos;
	}
	
	public static UserDetailsImpl build(Usuario usuario) {
		List<GrantedAuthority> acessos = usuario.getAcessos().stream().map(acesso -> new SimpleGrantedAuthority(acesso.getTipo().name()))
				.collect(Collectors.toList());
		
		return new UserDetailsImpl(
				usuario.getId(),
				usuario.getEmail(),
				usuario.getSenha(),
				acessos
				);
	}
	
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return acessos;
	}
	
	public Long getId() {
		return id;
	}

	@Override
	public String getPassword() {
		return senha;
	}

	@Override
	public String getUsername() {
		return email;
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

}
