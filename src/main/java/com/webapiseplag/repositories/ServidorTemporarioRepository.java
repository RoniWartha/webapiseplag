package com.webapiseplag.repositories;

import com.webapiseplag.domain.ServidorTemporario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ServidorTemporarioRepository extends JpaRepository<ServidorTemporario, Long> {
}