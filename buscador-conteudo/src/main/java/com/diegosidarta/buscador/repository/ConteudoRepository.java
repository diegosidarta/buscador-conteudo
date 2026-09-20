package com.diegosidarta.buscador.repository;

import com.diegosidarta.buscador.model.ConteudoEntity;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ConteudoRepository extends JpaRepository<ConteudoEntity, Long> {

    @Query("""
            SELECT DISTINCT c FROM ConteudoEntity c
            LEFT JOIN c.tags t
            WHERE (:humor IS NULL OR LOWER(t) = LOWER(:humor))
            AND (:duracaoMaxima IS NULL OR c.duracaoMinutos <= :duracaoMaxima)
            """)
    @EntityGraph(attributePaths = "tags")
    List<ConteudoEntity> buscarPorFiltros(@Param("humor") String humor,
                                          @Param("duracaoMaxima") Integer duracaoMaxima);
}