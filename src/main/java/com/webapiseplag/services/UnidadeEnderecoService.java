package com.webapiseplag.services;

import com.webapiseplag.domain.*;
import com.webapiseplag.dtos.UnidadeEnderecoDTO;
import com.webapiseplag.dtos.UnidadeEnderecoResponseDTO;
import com.webapiseplag.exceptions.DuplicateResourceException;
import com.webapiseplag.exceptions.ResourceNotFoundException;
import com.webapiseplag.repositories.EnderecoRepository;
import com.webapiseplag.repositories.UnidadeEnderecoRepository;
import com.webapiseplag.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UnidadeEnderecoService {

    private final UnidadeEnderecoRepository unidadeEnderecoRepository;
    private final UnidadeRepository unidadeRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public UnidadeEnderecoService(UnidadeEnderecoRepository unidadeEnderecoRepository,
                                  UnidadeRepository unidadeRepository,
                                  EnderecoRepository enderecoRepository) {
        this.unidadeEnderecoRepository = unidadeEnderecoRepository;
        this.unidadeRepository = unidadeRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public List<UnidadeEnderecoResponseDTO> findAll() {
        return unidadeEnderecoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public UnidadeEnderecoResponseDTO findById(Long id) {
        UnidadeEndereco unidadeEndereco = unidadeEnderecoRepository.findByIdWithAssociations(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relação Unidade-Endereço", id));
        return toDto(unidadeEndereco);
    }

    @Transactional
    public UnidadeEnderecoResponseDTO create(UnidadeEnderecoDTO dto) {
        if (unidadeEnderecoRepository.existsByUnidadeIdAndEnderecoId(dto.unidadeId(), dto.enderecoId())) {
            throw new DuplicateResourceException("Relação Unidade-Endereço já existe");
        }

        Unidade unidade = unidadeRepository.findById(dto.unidadeId())
                .orElseThrow(() -> new ResourceNotFoundException("Unidade", dto.unidadeId()));

        Endereco endereco = enderecoRepository.findById(dto.enderecoId())
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", dto.enderecoId()));

        UnidadeEndereco unidadeEndereco = new UnidadeEndereco();
        unidadeEndereco.setUnidade(unidade);
        unidadeEndereco.setEndereco(endereco);

        return toDto(unidadeEnderecoRepository.save(unidadeEndereco));
    }

    @Transactional(readOnly = true)
    public List<UnidadeEnderecoResponseDTO> findByUnidadeId(Long unidadeId) {
        return unidadeEnderecoRepository.findByUnidadeId(unidadeId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<UnidadeEnderecoResponseDTO> findByEnderecoId(Long enderecoId) {
        return unidadeEnderecoRepository.findByEnderecoId(enderecoId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        if (!unidadeEnderecoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Relação Unidade-Endereço", id);
        }
        unidadeEnderecoRepository.deleteById(id);
    }

    @Transactional
    public void deleteByUnidadeAndEndereco(Long unidadeId, Long enderecoId) {
        int deleted = unidadeEnderecoRepository.deleteByUnidadeIdAndEnderecoId(unidadeId, enderecoId);
        if (deleted == 0) {
            throw new ResourceNotFoundException("Relação Unidade-Endereço não encontrada");
        }
    }

    private UnidadeEnderecoResponseDTO toDto(UnidadeEndereco unidadeEndereco) {
        return new UnidadeEnderecoResponseDTO(
                unidadeEndereco.getId(),
                unidadeEndereco.getUnidade().getId(),
                unidadeEndereco.getUnidade().getNome(),
                unidadeEndereco.getEndereco().getId(),
                formatEndereco(unidadeEndereco.getEndereco())
        );
    }

    private String formatEndereco(Endereco endereco) {
        return String.format("%s %s, %s - %s/%s",
                endereco.getTipoLogradouro(),
                endereco.getLogradouro(),
                endereco.getNumero(),
                endereco.getCidade().getNome(),
                endereco.getCidade().getUf());
    }
}
