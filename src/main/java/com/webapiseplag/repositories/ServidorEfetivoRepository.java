package com.webapiseplag.repositories;

import com.webapiseplag.domain.Servidor;
import com.webapiseplag.domain.ServidorEfetivo;
import com.webapiseplag.dtos.EnderecoFuncionalDTO;
import com.webapiseplag.dtos.ServidorEfetivoUnidadeDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ServidorEfetivoRepository extends JpaRepository<ServidorEfetivo, Long> {
    boolean existsByMatricula(String matricula);

    @Query("""
        SELECT new com.webapiseplag.dtos.ServidorEfetivoUnidadeDTO(
            p.pes_nome, 
            YEAR(CURRENT_DATE) - YEAR(p.pes_data_nascimento) - 
            (CASE WHEN MONTH(CURRENT_DATE) < MONTH(p.pes_data_nascimento) OR 
                   (MONTH(CURRENT_DATE) = MONTH(p.pes_data_nascimento) AND DAY(CURRENT_DATE) < DAY(p.pes_data_nascimento)) 
              THEN 1 ELSE 0 END),
            u.unid_nome,
            f.fp_hash
        ) 
        FROM ServidorEfetivo s
        JOIN Pessoa p ON s.id = p.id
        JOIN Lotacao l ON s.id = l.pes_id
        JOIN Unidade u ON l.unid_id = u.unid_id
        LEFT JOIN FotoPessoa f ON p.id = f.pes_id
        WHERE l.unid_id = :unidId
    """)
    List<ServidorEfetivoUnidadeDTO> findByUnidadeId(@Param("unidId") Long unidId);

    @Query("""
        SELECT new com.webapiseplag.dtos.EnderecoFuncionalDTO(
            p.pesNome, u.unidNome, u.unidSigla, e.endTipoLogradouro, e.endLogradouro, e.endNumero, 
            e.endBairro, c.cidNome, c.cidUf)
        FROM Pessoa p
        JOIN ServidorEfetivo se ON p.pesId = se.pesId
        JOIN Lotacao l ON p.pesId = l.pesId
        JOIN Unidade u ON l.unidId = u.unidId
        JOIN UnidadeEndereco ue ON u.unidId = ue.unidId
        JOIN Endereco e ON ue.endId = e.endId
        JOIN Cidade c ON e.cidId = c.cidId
        WHERE LOWER(p.pesNome) LIKE LOWER(CONCAT('%', :nome, '%'))
    """)
    List<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNome(@Param("nome") String nome);
}