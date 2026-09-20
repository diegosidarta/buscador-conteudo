package com.diegosidarta.buscador.service;

import com.diegosidarta.buscador.model.Conteudo;
import com.diegosidarta.buscador.repository.ConteudoRepository;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.ai.tool.annotation.ToolParam;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CatalogoTools {

    private final ConteudoRepository repository;

    public CatalogoTools(ConteudoRepository repository) {
        this.repository = repository;
    }

    @Tool(description = """
            Busca conteúdos do catálogo que combinam com o humor/contexto
            atual do usuário e com o tempo máximo que ele tem disponível.
            Use SEMPRE essa ferramenta antes de recomendar algo — nunca
            invente um título que não veio dela.
            """)
    public List<Conteudo> buscarConteudo(
            @ToolParam(description = "Humor ou contexto do usuário, ex: cansado, animado, curioso, relaxado")
            String humor,
            @ToolParam(description = "Tempo máximo disponível em minutos, pode ser nulo se o usuário não disse")
            Integer duracaoMaximaMinutos
    ) {
        String humorFiltro = (humor == null || humor.isBlank()) ? null : humor.trim();

        return repository.buscarPorFiltros(humorFiltro, duracaoMaximaMinutos).stream()
                .map(e -> new Conteudo(
                        e.getTitulo(),
                        e.getGenero(),
                        e.getDuracaoMinutos(),
                        List.copyOf(e.getTags())))
                .toList();
    }
}