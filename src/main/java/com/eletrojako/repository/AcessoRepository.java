package com.eletrojako.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.eletrojako.models.Acesso;
import com.eletrojako.models.Acesso.TipoDeAcesso;


@Repository
public interface AcessoRepository extends JpaRepository<Acesso, Long> {
	Optional<Acesso> findByAcesso(TipoDeAcesso acesso);
}
