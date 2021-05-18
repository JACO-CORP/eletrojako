package com.eletrojako.controller;


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

import com.eletrojako.models.Compra;
import com.eletrojako.models.Produto;
import com.eletrojako.models.Usuario;
import com.eletrojako.repository.CompraRepository;
import com.eletrojako.repository.ProdutoRepository;
import com.eletrojako.repository.UsuarioRepository;

@RestController
@RequestMapping("compra")
public class CompraController {
	
	@Autowired
	private CompraRepository repository;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@GetMapping("/")
	public List<Compra> getCompras() {
		return repository.findAll();
	}
	
	@GetMapping("/{id}")
	public Optional<Compra> getCompra(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/criar")
	public String criarCompra(@RequestBody Compra compra) {
		Produto novo = produtoRepository.getOne(compra.getId_produto());
		novo.setQuantidade_Estoque(novo.getQuantidade_Estoque() - 1);
		
		produtoRepository.save(novo);
		repository.save(compra);
		
		return (compra.toString() + " " + "novo saldo: " + novo.getQuantidade_Estoque());	
	}
	
	@PutMapping("/atualizar/{id}")
	public Compra atualizarCompra(@PathVariable int id, @RequestBody Compra compra) {
		compra.setId( (long) id);
		
		repository.save(compra);
		
		return compra;
	}
	
	@DeleteMapping("deletar/{id}")
	public Compra deletarCompra(@PathVariable int id) {
		Compra compra = repository.findById( (long) id).get();
		
		repository.delete(compra);
		return compra;
	}
	
}
