package com.webapiseplag.services;

import com.webapiseplag.domain.*;
import com.webapiseplag.dtos.LotacaoDTO;
import com.webapiseplag.dtos.LotacaoResponseDTO;
import com.webapiseplag.exceptions.ResourceNotFoundException;
import com.webapiseplag.repositories.LotacaoRepository;
import com.webapiseplag.repositories.PessoaRepository;
import com.webapiseplag.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class LotacaoService {

    private final LotacaoRepository lotacaoRepository;
    private final PessoaRepository pessoaRepository;
    private final UnidadeRepository unidadeRepository;

    @Autowired
    public LotacaoService(LotacaoRepository lotacaoRepository,
                          PessoaRepository pessoaRepository,
                          UnidadeRepository unidadeRepository) {
        this.lotacaoRepository = lotacaoRepository;
        this.pessoaRepository = pessoaRepository;
        this.unidadeRepository = unidadeRepository;
    }

    @Transactional(readOnly = true)
    public List<LotacaoResponseDTO> findAll() {
        return lotacaoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public LotacaoResponseDTO findById(Long id) {
        Lotacao lotacao = lotacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lotação", id));
        return toDto(lotacao);
    }

    @Transactional
    public LotacaoResponseDTO create(LotacaoDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", dto.pessoaId()));

        Unidade unidade = unidadeRepository.findById(dto.unidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Unidade", dto.unidadeId()));

        Lotacao lotacao = new Lotacao();
        lotacao.setPessoa(pessoa);
        lotacao.setUnidade(unidade);
        lotacao.setDataLotacao(dto.dataLotacao());
        lotacao.setDataRemocao(dto.dataRemocao());
        lotacao.setPortaria(dto.portaria());

        return toDto(lotacaoRepository.save(lotacao));
    }

    @Transactional
    public LotacaoResponseDTO update(Long id, LotacaoDTO dto) {
        Lotacao lotacao = lotacaoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Lotação", id));

        if (dto.unidadeId() != null) {
            Unidade unidade = unidadeRepository.findById(dto.unidadeId())
                    .orElseThrow(() -> new ResourceNotFoundException("Unidade", dto.unidadeId()));
            lotacao.setUnidade(unidade);
        }

        if (dto.dataLotacao() != null) {
            lotacao.setDataLotacao(dto.dataLotacao());
        }

        lotacao.setDataRemocao(dto.dataRemocao());
        lotacao.setPortaria(dto.portaria());

        return toDto(lotacaoRepository.save(lotacao));
    }

    @Transactional
    public void delete(Long id) {
        if (!lotacaoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Lotação", id);
        }
        lotacaoRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public List<LotacaoResponseDTO> findByPessoaId(Long pessoaId) {
        return lotacaoRepository.findByPessoaId(pessoaId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<LotacaoResponseDTO> findByUnidadeId(Long unidadeId) {
        return lotacaoRepository.findByUnidadeId(unidadeId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    private LotacaoResponseDTO toDto(Lotacao lotacao) {
        return new LotacaoResponseDTO(
                lotacao.getId(),
                lotacao.getPessoa().getId(),
                lotacao.getPessoa().getNome(),
                lotacao.getUnidade().getId(),
                lotacao.getUnidade().getNome(),
                lotacao.getDataLotacao(),
                lotacao.getDataRemocao(),
                lotacao.getPortaria()
        );
    }
}