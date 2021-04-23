package com.eletrojako.payload.response;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class JwtResponse {

	private String token;
	private String type = "Bearer";
	private Long id;
	private String email;
	
	private List<String> acessos;
	
	public JwtResponse(String accessToken, Long id, String email, List<String> acessos) {
		this.token = accessToken;
		this.id = id;
		this.email = email;
		this.acessos = acessos;
	}

	public String getToken() {
		return token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public List<String> getAcessos() {
		return acessos;
	}

	public void setAcessos(List<String> acessos) {
		this.acessos = acessos;
	}
}
