package com.eletrojako.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.eletrojako.models.Usuario;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
	
}
