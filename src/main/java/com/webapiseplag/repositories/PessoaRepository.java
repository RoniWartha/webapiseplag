package com.webapiseplag.repositories;

import com.webapiseplag.domain.Pessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PessoaRepository extends JpaRepository<Pessoa, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário
}
