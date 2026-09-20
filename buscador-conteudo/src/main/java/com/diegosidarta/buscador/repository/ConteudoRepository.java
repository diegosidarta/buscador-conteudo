package com.diegosidarta.buscador.repository;

import com.diegosidarta.buscador.model.ConteudoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ConteudoRepository extends JpaRepository<ConteudoEntity, Long> {

}
