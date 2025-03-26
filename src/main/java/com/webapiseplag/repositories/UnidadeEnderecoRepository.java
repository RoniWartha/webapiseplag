package com.webapiseplag.repositories;

import com.webapiseplag.domain.UnidadeEndereco;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface UnidadeEnderecoRepository extends JpaRepository<UnidadeEndereco, Long> {

    boolean existsByUnidadeIdAndEnderecoId(Long unidadeId, Long enderecoId);

    List<UnidadeEndereco> findByUnidadeId(Long unidadeId);

    List<UnidadeEndereco> findByEnderecoId(Long enderecoId);

    @Modifying
    @Query("DELETE FROM UnidadeEndereco ue WHERE ue.unidade.id = :unidadeId AND ue.endereco.id = :enderecoId")
    int deleteByUnidadeIdAndEnderecoId(@Param("unidadeId") Long unidadeId,
                                       @Param("enderecoId") Long enderecoId);

    @Query("SELECT ue FROM UnidadeEndereco ue JOIN FETCH ue.unidade JOIN FETCH ue.endereco WHERE ue.id = :id")
    Optional<UnidadeEndereco> findByIdWithAssociations(@Param("id") Long id);
}
