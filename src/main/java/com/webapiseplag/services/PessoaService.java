package com.webapiseplag.services;

import com.webapiseplag.domain.Pessoa;
import com.webapiseplag.repositories.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class PessoaService {

    @Autowired
    private PessoaRepository pessoaRepository;

    public List<Pessoa> getAll() {
        return pessoaRepository.findAll();
    }

    public Optional<Pessoa> findById(Long id) {
        return pessoaRepository.findById(id);
    }

    public Pessoa create(Pessoa pessoa) {
        return pessoaRepository.save(pessoa);
    }

    public Pessoa update(Long id, Pessoa newPessoa) {
        return pessoaRepository.findById(id)
                .map(pessoa -> {
                    pessoa.setNome(newPessoa.getNome());
                    pessoa.setDataNascimento(newPessoa.getDataNascimento());
                    pessoa.setSexo(newPessoa.getSexo());
                    pessoa.setMae(newPessoa.getMae());
                    pessoa.setPai(newPessoa.getPai());
                    return pessoaRepository.save(pessoa);
                })
                .orElseThrow(() -> new RuntimeException("Pessoa não encontrada com o ID: " + id));
    }

    public void delete(Long id) {
        if (pessoaRepository.existsById(id)) {
            pessoaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Pessoa não encontrada com o ID: " + id);
        }
    }
}
