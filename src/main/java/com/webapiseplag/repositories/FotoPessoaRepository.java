package com.webapiseplag.repositories;

import com.webapiseplag.domain.FotoPessoa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FotoPessoaRepository extends JpaRepository<FotoPessoa, Long> {
    // Métodos personalizados podem ser adicionados aqui, se necessário

}