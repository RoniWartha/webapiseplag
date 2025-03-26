package com.webapiseplag.services;

import com.webapiseplag.domain.*;
import com.webapiseplag.dtos.EnderecoFuncionalDTO;
import com.webapiseplag.dtos.ServidorEfetivoDTO;
import com.webapiseplag.dtos.ServidorEfetivoResponseDTO;
import com.webapiseplag.dtos.ServidorEfetivoUnidadeDTO;
import com.webapiseplag.exceptions.ResourceNotFoundException;
import com.webapiseplag.repositories.PessoaRepository;
import com.webapiseplag.repositories.ServidorEfetivoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.Period;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServidorEfetivoService {

    private final ServidorEfetivoRepository servidorEfetivoRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public ServidorEfetivoService(ServidorEfetivoRepository servidorEfetivoRepository,
                                  PessoaRepository pessoaRepository) {
        this.servidorEfetivoRepository = servidorEfetivoRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<ServidorEfetivoResponseDTO> findAll() {
        return servidorEfetivoRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServidorEfetivoResponseDTO findById(Long id) {
        ServidorEfetivo servidor = servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servidor Efetivo", id));
        return toDto(servidor);
    }

    @Transactional
    public ServidorEfetivoResponseDTO create(ServidorEfetivoDTO dto) {
        if (servidorEfetivoRepository.existsByMatricula(dto.matricula())) {
            throw new IllegalArgumentException("Matrícula já cadastrada");
        }

        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", dto.pessoaId()));

        ServidorEfetivo servidor = new ServidorEfetivo();
        servidor.setPessoa(pessoa);
        servidor.setMatricula(dto.matricula());

        ServidorEfetivo saved = servidorEfetivoRepository.save(servidor);
        return toDto(saved);
    }

    @Transactional
    public ServidorEfetivoResponseDTO update(Long id, ServidorEfetivoDTO dto) {
        ServidorEfetivo servidor = servidorEfetivoRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servidor Efetivo", id));

        if (dto.matricula() != null && !dto.matricula().equals(servidor.getMatricula())) {
            if (servidorEfetivoRepository.existsByMatricula(dto.matricula())) {
                throw new IllegalArgumentException("Nova matrícula já está em uso");
            }
            servidor.setMatricula(dto.matricula());
        }

        return toDto(servidorEfetivoRepository.save(servidor));
    }

    @Transactional
    public void delete(Long id) {
        if (!servidorEfetivoRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servidor Efetivo", id);
        }
        servidorEfetivoRepository.deleteById(id);
    }

    private ServidorEfetivoResponseDTO toDto(ServidorEfetivo servidor) {
        return new ServidorEfetivoResponseDTO(
                servidor.getId(),
                servidor.getPessoa().getId(),
                servidor.getPessoa().getNome(),
                servidor.getMatricula()
        );
    }

    @Transactional(readOnly = true)
    public List<ServidorEfetivoUnidadeDTO> findByUnidadeId(Long unidId) {
        return servidorEfetivoRepository.findByUnidadeId(unidId);
    }

    public List<EnderecoFuncionalDTO> buscarEnderecoFuncionalPorNome(String nome) {
        return servidorEfetivoRepository.buscarEnderecoFuncionalPorNome(nome);
    }
}
