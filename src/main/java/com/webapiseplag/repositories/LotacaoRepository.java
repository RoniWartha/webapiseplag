package com.webapiseplag.repositories;

import com.webapiseplag.domain.Lotacao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface LotacaoRepository extends JpaRepository<Lotacao, Long> {
    List<Lotacao> findByPessoaId(Long pessoaId);
    List<Lotacao> findByUnidadeId(Long unidadeId);
    boolean existsByPessoaIdAndDataRemocaoIsNull(Long pessoaId);
}
