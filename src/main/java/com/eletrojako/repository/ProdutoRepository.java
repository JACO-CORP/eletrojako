package com.eletrojako.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eletrojako.models.Produto;

public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	
}
