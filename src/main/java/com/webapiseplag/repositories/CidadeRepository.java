package com.webapiseplag.repositories;

import com.webapiseplag.domain.Cidade;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}