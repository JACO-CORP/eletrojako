package com.eletrojako.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "acesso")
public class Acesso {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	 
	@Enumerated(EnumType.STRING)
	@Column(length = 50)
	private TipoDeAcesso acesso;
	
	public enum TipoDeAcesso {
		USUARIO,
		ADMIN
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public TipoDeAcesso getTipo() {
		return acesso;
	}

	public void setTipo(TipoDeAcesso acesso) {
		this.acesso = acesso;
	}
}
