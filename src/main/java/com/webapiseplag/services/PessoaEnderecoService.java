package com.webapiseplag.services;

import com.webapiseplag.domain.*;
import com.webapiseplag.dtos.PessoaEnderecoResponseDTO;
import com.webapiseplag.exceptions.DuplicateResourceException;
import com.webapiseplag.exceptions.ResourceNotFoundException;
import com.webapiseplag.exceptions.OperationNotAllowedException;
import com.webapiseplag.repositories.EnderecoRepository;
import com.webapiseplag.repositories.PessoaEnderecoRepository;
import com.webapiseplag.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class PessoaEnderecoService {

    private final PessoaEnderecoRepository pessoaEnderecoRepository;
    private final PessoaRepository pessoaRepository;
    private final EnderecoRepository enderecoRepository;

    @Autowired
    public PessoaEnderecoService(PessoaEnderecoRepository pessoaEnderecoRepository,
                                 PessoaRepository pessoaRepository,
                                 EnderecoRepository enderecoRepository) {
        this.pessoaEnderecoRepository = pessoaEnderecoRepository;
        this.pessoaRepository = pessoaRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Transactional(readOnly = true)
    public List<PessoaEnderecoResponseDTO> findAll() {
        return pessoaEnderecoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public PessoaEnderecoResponseDTO findById(Long id) {
        return pessoaEnderecoRepository.findByIdWithAssociations(id)
                .map(this::toDto)
                .orElseThrow(() -> new ResourceNotFoundException("Relação Pessoa-Endereço", id));
    }

    @Transactional
    public PessoaEnderecoResponseDTO create(Long pessoaId, Long enderecoId) {
        if (pessoaEnderecoRepository.existsByPessoaIdAndEnderecoId(pessoaId, enderecoId)) {
            throw new DuplicateResourceException(
                    "Relação Pessoa-Endereço",
                    "pessoaId=" + pessoaId + " e enderecoId=" + enderecoId
            );
        }

        Pessoa pessoa = pessoaRepository.findById(pessoaId)
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", pessoaId));

        Endereco endereco = enderecoRepository.findById(enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException("Endereço", enderecoId));

        PessoaEndereco pessoaEndereco = new PessoaEndereco();
        pessoaEndereco.associar(pessoa, endereco);

        return toDto(pessoaEnderecoRepository.save(pessoaEndereco));
    }

    @Transactional(readOnly = true)
    public List<PessoaEnderecoResponseDTO> findByPessoaId(Long pessoaId) {
        if (!pessoaRepository.existsById(pessoaId)) {
            throw new ResourceNotFoundException("Pessoa", pessoaId);
        }
        return pessoaEnderecoRepository.findByPessoaId(pessoaId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public List<PessoaEnderecoResponseDTO> findByEnderecoId(Long enderecoId) {
        if (!enderecoRepository.existsById(enderecoId)) {
            throw new ResourceNotFoundException("Endereço", enderecoId);
        }
        return pessoaEnderecoRepository.findByEnderecoId(enderecoId).stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional
    public void delete(Long id) {
        PessoaEndereco pessoaEndereco = pessoaEnderecoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Relação Pessoa-Endereço", id));

        pessoaEnderecoRepository.delete(pessoaEndereco);
    }

    @Transactional
    public void deleteByPessoaAndEndereco(Long pessoaId, Long enderecoId) {
        PessoaEndereco pessoaEndereco = pessoaEnderecoRepository
                .findByPessoaIdAndEnderecoId(pessoaId, enderecoId)
                .orElseThrow(() -> new ResourceNotFoundException(
                        String.format("Relação Pessoa (ID %s) com Endereço (ID %s)", pessoaId, enderecoId)
                ));

        pessoaEnderecoRepository.delete(pessoaEndereco);
    }

    @Transactional(readOnly = true)
    public boolean pessoaHasAnyEndereco(Long pessoaId) {
        return pessoaEnderecoRepository.existsByPessoaId(pessoaId);
    }

    @Transactional(readOnly = true)
    public boolean enderecoHasAnyPessoa(Long enderecoId) {
        return pessoaEnderecoRepository.existsByEnderecoId(enderecoId);
    }

    private PessoaEnderecoResponseDTO toDto(PessoaEndereco pessoaEndereco) {
        return new PessoaEnderecoResponseDTO(
                pessoaEndereco.getId(),
                pessoaEndereco.getPessoa().getId(),
                pessoaEndereco.getEndereco().getId(),
                pessoaEndereco.getPessoa().getNome(),
                pessoaEndereco.getEndereco().getLogradouro()
        );
    }
}