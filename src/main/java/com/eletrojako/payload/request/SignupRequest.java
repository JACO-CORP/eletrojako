package com.eletrojako.payload.request;

import java.util.Set;

public class SignupRequest {
	private String email;
	private String senha;
	
	private Set<String> acessos;

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public Set<String> getAcessos() {
		return acessos;
	}

	public void setAcessos(Set<String> acessos) {
		this.acessos = acessos;
	}
	
	
}
