package com.eletrojako.controller;

import com.eletrojako.models.Endereco;
import com.eletrojako.repository.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("endereco")
public class EnderecoController {

    @Autowired
    private EnderecoRepository repository;

    @GetMapping("/")
    public List<Endereco> getEnderecos() {
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Endereco> getEndereco(@PathVariable Long id) {
        return repository.findById(id);
    }

    @PostMapping("/criar")
    public Endereco criarEndereco(@RequestBody Endereco endereco) {
        return repository.save(endereco);
    }

    @PutMapping("/atualizar/{id}")
    public Endereco atualizarEndereco(@PathVariable int id, @RequestBody Endereco endereco) {
        endereco.setId( (long) id);

        repository.save(endereco);
        return endereco;
    }

    @DeleteMapping("/deletar/{id}")
    public Endereco deletarEndereco(@PathVariable int id) {
        Endereco endereco = repository.findById( (long) id ).get();

        repository.delete(endereco);
        return endereco;
    }
}
