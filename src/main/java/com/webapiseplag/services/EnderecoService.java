package com.webapiseplag.services;

import com.webapiseplag.domain.Endereco;
import com.webapiseplag.repositories.EnderecoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class EnderecoService {

    @Autowired
    private EnderecoRepository enderecoRepository;

    public List<Endereco> getAll() {
        return enderecoRepository.findAll();
    }

    public Optional<Endereco> findById(Long id) {
        return enderecoRepository.findById(id);
    }

    public Endereco create(Endereco endereco) {
        return enderecoRepository.save(endereco);
    }

    public Endereco update(Long id, Endereco newEndereco) {
        return enderecoRepository.findById(id)
                .map(endereco -> {
                    endereco.setTipoLogradouro(newEndereco.getTipoLogradouro());
                    endereco.setLogradouro(newEndereco.getLogradouro());
                    endereco.setNumero(newEndereco.getNumero());
                    endereco.setBairro(newEndereco.getBairro());
                    endereco.setCidade(newEndereco.getCidade());
                    return enderecoRepository.save(endereco);
                })
                .orElseThrow(() -> new RuntimeException("Endereço não encontrado com o ID: " + id));
    }

    public void delete(Long id) {
        if (enderecoRepository.existsById(id)) {
            enderecoRepository.deleteById(id);
        } else {
            throw new RuntimeException("Endereço não encontrado com o ID: " + id);
        }
    }
}