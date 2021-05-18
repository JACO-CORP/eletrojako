package com.eletrojako.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "produto")
public class Produto {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long id;
	
	@Column(name = "nome")
	public String nome;
	
	@Column(name = "descricao")
	public String descricao;	
	
	@Column(name = "valor_Unidade")
	public float valor_Unidade;
	
	@Column(name = "quantidade_Estoque")
	public int quantidade_Estoque;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public float getValor_Unidade() {
		return valor_Unidade;
	}

	public void setValor_Unidade(float valor_Unidade) {
		this.valor_Unidade = valor_Unidade;
	}

	public int getQuantidade_Estoque() {
		return quantidade_Estoque;
	}

	public void setQuantidade_Estoque(int quantidade_Estoque) {
		this.quantidade_Estoque = quantidade_Estoque;
	}
}
