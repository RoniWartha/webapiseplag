package com.webapiseplag.services;

import com.webapiseplag.domain.*;
import com.webapiseplag.dtos.ServidorTemporarioDTO;
import com.webapiseplag.dtos.ServidorTemporarioResponseDTO;
import com.webapiseplag.exceptions.ResourceNotFoundException;
import com.webapiseplag.repositories.PessoaRepository;
import com.webapiseplag.repositories.ServidorTemporarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ServidorTemporarioService {

    private final ServidorTemporarioRepository servidorTemporarioRepository;
    private final PessoaRepository pessoaRepository;

    @Autowired
    public ServidorTemporarioService(ServidorTemporarioRepository servidorTemporarioRepository,
                                     PessoaRepository pessoaRepository) {
        this.servidorTemporarioRepository = servidorTemporarioRepository;
        this.pessoaRepository = pessoaRepository;
    }

    @Transactional(readOnly = true)
    public List<ServidorTemporarioResponseDTO> findAll() {
        return servidorTemporarioRepository.findAll().stream()
                .map(this::toDto)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public ServidorTemporarioResponseDTO findById(Long id) {
        ServidorTemporario servidor = servidorTemporarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servidor Temporário", id));
        return toDto(servidor);
    }

    @Transactional
    public ServidorTemporarioResponseDTO create(ServidorTemporarioDTO dto) {
        Pessoa pessoa = pessoaRepository.findById(dto.pessoaId())
                .orElseThrow(() -> new ResourceNotFoundException("Pessoa", dto.pessoaId()));

        if (servidorTemporarioRepository.existsById(dto.pessoaId())) {
            throw new IllegalStateException("Já existe um servidor temporário cadastrado para esta pessoa");
        }

        ServidorTemporario servidor = new ServidorTemporario();
        servidor.setPessoa(pessoa);
        servidor.setDataAdmissao(dto.dataAdmissao());
        servidor.setDataDemissao(dto.dataDemissao());

        ServidorTemporario saved = servidorTemporarioRepository.save(servidor);
        return toDto(saved);
    }

    @Transactional
    public ServidorTemporarioResponseDTO update(Long id, ServidorTemporarioDTO dto) {
        ServidorTemporario servidor = servidorTemporarioRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Servidor Temporário", id));

        if (dto.dataAdmissao() != null) {
            servidor.setDataAdmissao(dto.dataAdmissao());
        }

        servidor.setDataDemissao(dto.dataDemissao());

        return toDto(servidorTemporarioRepository.save(servidor));
    }

    @Transactional
    public void delete(Long id) {
        if (!servidorTemporarioRepository.existsById(id)) {
            throw new ResourceNotFoundException("Servidor Temporário", id);
        }
        servidorTemporarioRepository.deleteById(id);
    }

    @Transactional(readOnly = true)
    public boolean existsByPessoaId(Long pessoaId) {
        return servidorTemporarioRepository.existsById(pessoaId);
    }

    private ServidorTemporarioResponseDTO toDto(ServidorTemporario servidor) {
        return new ServidorTemporarioResponseDTO(
                servidor.getId(),
                servidor.getPessoa().getId(),
                servidor.getPessoa().getNome(),
                servidor.getDataAdmissao(),
                servidor.getDataDemissao()
        );
    }
}