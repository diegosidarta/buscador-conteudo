package com.diegosidarta.buscador.controller;

import com.diegosidarta.buscador.model.ConteudoEntity;
import com.diegosidarta.buscador.repository.ConteudoRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ConteudoController {

    private final ConteudoRepository repository;

    public ConteudoController(ConteudoRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/conteudos")
    public List<ConteudoEntity> listar() {
        return repository.findAll();
    }
}
