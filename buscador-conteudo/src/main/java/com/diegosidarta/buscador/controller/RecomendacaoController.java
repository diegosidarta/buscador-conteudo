package com.diegosidarta.buscador.controller;

import com.diegosidarta.buscador.model.PedidoRecomendacao;
import com.diegosidarta.buscador.service.CatalogoTools;
import org.springframework.ai.chat.client.ChatClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recomendacao")
public class RecomendacaoController {

    private final ChatClient chatClient;

    public RecomendacaoController(ChatClient.Builder chatClientBuilder, CatalogoTools catalogoTools) {
        this.chatClient = chatClientBuilder
                .defaultSystem("""
                Você recomenda conteúdo de um catálogo de streaming.
                Sempre use a ferramenta de busca no catálogo antes de responder. Nunca recomende algo que não veio dela.
                Responda em poucas frases, de forma natural.
                """)
                .defaultTools(catalogoTools)
                .build();
    }

    @PostMapping
    public String recomendar(@RequestBody PedidoRecomendacao pedido) {
        return chatClient.prompt()
                .user(pedido.mensagem())
                .call()
                .content();
    }
}

