package com.diegosidarta.buscador.model;

import java.util.List;


// Record classe imutável em uma linha: o Java gera construtor, getters, equals , hashCode e toString .
// Perfeito para transportar dados.
public record Conteudo(String titulo,
                       String genero,
                       int duracaoMinutos,
                       List<String> tags) {
}
