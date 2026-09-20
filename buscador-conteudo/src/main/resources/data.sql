 INSERT INTO conteudo (id, titulo, genero, duracao_minutos) VALUES
 (1, 'Sessão de Comédia Stand-up', 'Comédia', 15),
 (2, 'Documentário: Bastidores do Futebol', 'Documentário', 45),
 (3, 'Minissérie de Suspense - Ep. 1', 'Suspense', 50),
 (4, 'Desenho Animado Clássico', 'Infantil', 20),
 (5, 'Novela - Capítulo do Dia', 'Novela', 40),
 (6, 'Podcast em Vídeo: Carreira em Tech', 'Talk', 30),
 (7, 'Reality Show - Melhores Momentos', 'Reality', 12),
 (8, 'Filme de Ação - Longa', 'Ação', 110),
 (9, 'Microdrama Vertical - Temporada Curta', 'Drama', 8),
 (10, 'Jornalismo - Resumo do Dia', 'Jornalismo', 10);

 INSERT INTO conteudo_tags (conteudo_id, tag) VALUES
 (1, 'leve'), (1, 'rápido'), (1, 'cansado'),
 (2, 'relaxado'), (2, 'curioso'),
 (3, 'animado'), (3, 'tenso'),
 (4, 'leve'), (4, 'nostálgico'), (4, 'cansado'),
 (5, 'relaxado'), (5, 'nostálgico'),
 (6, 'curioso'), (6, 'produtivo'),
 (7, 'leve'), (7, 'cansado'), (7, 'rápido'),
 (8, 'animado'), (8, 'empolgado'),
 (9, 'rápido'), (9, 'cansado'), (9, 'leve'),
 (10, 'informado'), (10, 'rápido');

 -- Os ids foram informados na mão, então a sequence do PostgreSQL continuaria no 1
 -- e um save() futuro colidiria. Esta linha reposiciona a sequence.
 SELECT setval(pg_get_serial_sequence('conteudo', 'id'), (SELECT MAX(id) FROM conteudo));