package com.diegosidarta.buscador.model;

import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "conteudo")
public class ConteudoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;
    private String genero;
    private int duracaoMinutos;

    @ElementCollection
    @CollectionTable(name = "conteudo_tags", joinColumns = @JoinColumn(name = "conteudo_id"))
    @Column(name = "tag", nullable = false)
    private Set<String> tags = new HashSet<>();

    protected ConteudoEntity() {
        // o JPA exige um construtor vazio para conseguir instanciar a classe
    }

    public ConteudoEntity(String titulo, String genero, int duracaoMinutos, Set<String> tags) {
        this.titulo = titulo;
        this.genero = genero;
        this.duracaoMinutos = duracaoMinutos;
        this.tags = (tags == null) ? new HashSet<>() : new HashSet<>(tags);
    }

    public Long getId() {
        return id;
    }

    public String getTitulo() {
        return titulo;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracaoMinutos() {
        return duracaoMinutos;
    }

    public Set<String> getTags() {
        return tags;
    }
}