package com.webapiseplag.repositories;

import com.webapiseplag.domain.PessoaEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface PessoaEnderecoRepository extends JpaRepository<PessoaEndereco, Long> {

    boolean existsByPessoaIdAndEnderecoId(Long pessoaId, Long enderecoId);

    List<PessoaEndereco> findByPessoaId(Long pessoaId);

    List<PessoaEndereco> findByEnderecoId(Long enderecoId);

    @Modifying
    @Query("DELETE FROM PessoaEndereco pe WHERE pe.pessoa.id = :pessoaId AND pe.endereco.id = :enderecoId")
    int deleteByPessoaIdAndEnderecoId(@Param("pessoaId") Long pessoaId,
                                      @Param("enderecoId") Long enderecoId);

    @Query("SELECT pe FROM PessoaEndereco pe JOIN FETCH pe.pessoa JOIN FETCH pe.endereco WHERE pe.id = :id")
    Optional<PessoaEndereco> findByIdWithAssociations(@Param("id") Long id);

    @Query("SELECT COUNT(pe) > 0 FROM PessoaEndereco pe WHERE pe.pessoa.id = :pessoaId")
    boolean existsByPessoaId(@Param("pessoaId") Long pessoaId);

    @Query("SELECT COUNT(pe) > 0 FROM PessoaEndereco pe WHERE pe.endereco.id = :enderecoId")
    boolean existsByEnderecoId(@Param("enderecoId") Long enderecoId);

    @Query("SELECT pe FROM PessoaEndereco pe JOIN FETCH pe.pessoa p JOIN FETCH pe.endereco e " +
            "WHERE p.id = :pessoaId AND e.id = :enderecoId")
    Optional<PessoaEndereco> findByPessoaIdAndEnderecoId(@Param("pessoaId") Long pessoaId,
                                                         @Param("enderecoId") Long enderecoId);
}
