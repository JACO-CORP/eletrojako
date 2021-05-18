package com.eletrojako.controller;

import java.io.Console;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletrojako.models.Produto;
import com.eletrojako.repository.ProdutoRepository;

@RestController
@RequestMapping("produto")
public class ProdutoController {
	
	@Autowired
	private ProdutoRepository repository;
	
	@GetMapping("/")
	public List<Produto> getProdutos() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Produto> getProduto(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/criar")
	public Produto criarProduto(@RequestBody Produto produto) {
		return repository.save(produto);
	}
	
	@PutMapping("/atualizar/{id}")
	public Produto atualizarProduto(@PathVariable int id, @RequestBody Produto produto) {
		produto.setId( (long) id);


		repository.save(produto);
		
		return produto;
	}
	
	@DeleteMapping("/deletar/{id}")
	public Produto deletarProduto(@PathVariable int id) {
		Produto produto = repository.findById((long) id).get();
		
		repository.delete(produto);
		return produto;
	}

}
