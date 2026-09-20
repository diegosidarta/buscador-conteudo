# Buscador de Conteúdo por Contexto

API em Spring Boot que recomenda conteúdo de um catálogo a partir de uma frase
em linguagem natural, usando Tool Calling do Spring AI e PostgreSQL.

## Como funciona

1. `POST /recomendacao` recebe a mensagem livre.
2. O `ChatClient` (Spring AI) interpreta o pedido e aciona a ferramenta `buscarConteudo`.
3. A ferramenta consulta o PostgreSQL (JPQL com `@Query`).
4. A IA escreve a resposta a partir dos itens reais devolvidos.

## Como rodar

1. Suba o PostgreSQL (o projeto espera a porta 5434 e o banco `buscador`):

```
docker run --name buscador-db -e POSTGRES_PASSWORD=<sua-senha> -e POSTGRES_DB=buscador -p 5434:5432 -d postgres
```

2. Defina as variáveis de ambiente `OPENAI_API_KEY` e `DB_PASSWORD`.
3. Dentro da pasta `buscador-conteudo` (a do `pom.xml`), rode `mvn spring-boot:run` e envie um POST para `/recomendacao` com o corpo:

```
{"mensagem": "tô cansado, tenho uns 20 minutos, quero algo leve"}
```

## Decisões técnicas

- Filtro feito no banco (`@Query`), e não em memória com `findAll()`.
- Tags como `Set` (e não `List`), com carregamento LAZY e `@EntityGraph` para evitar N+1.
- DTO (`record`) separado da entidade.
- Grounding em duas camadas: descrição do `@Tool` e system prompt.

## Limitações conscientes

- Sem paginação nem limite de itens devolvidos ao modelo.
- `ddl-auto=create-drop` em vez de Flyway.
- Sem cache, autenticação ou controle de resiliência na chamada externa.
- O grounding reduz o risco de a IA inventar títulos, mas não o elimina: continua sendo um LLM.