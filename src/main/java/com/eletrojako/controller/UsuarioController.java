package com.eletrojako.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.eletrojako.models.Usuario;
import com.eletrojako.repository.UsuarioRepository;

@RestController
@RequestMapping("usuario")
public class UsuarioController {

	@Autowired
	private UsuarioRepository repository;
	
	@GetMapping("/{id}")
	public Optional<Usuario> getUsuario(@PathVariable Long id) {
		return repository.findById(id);
	}
	
	@PostMapping("/criar")
	public Usuario criarUsuario(@RequestBody Usuario usuario) {
		return repository.save(usuario);
	}
	
}
