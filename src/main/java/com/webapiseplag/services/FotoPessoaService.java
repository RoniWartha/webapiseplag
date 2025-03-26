package com.webapiseplag.services;

import com.webapiseplag.domain.FotoPessoa;
import com.webapiseplag.repositories.FotoPessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class FotoPessoaService {

    @Autowired
    private FotoPessoaRepository fotoPessoaRepository;

    public List<FotoPessoa> getAll() {
        return fotoPessoaRepository.findAll();
    }

    public Optional<FotoPessoa> findById(Long id) {
        return fotoPessoaRepository.findById(id);
    }

    public FotoPessoa create(FotoPessoa fotoPessoa) {
        return fotoPessoaRepository.save(fotoPessoa);
    }

    public FotoPessoa update(Long id, FotoPessoa newFotoPessoa) {
        return fotoPessoaRepository.findById(id)
                .map(fotoPessoa -> {
                    fotoPessoa.setPessoa(newFotoPessoa.getPessoa());
                    fotoPessoa.setData(newFotoPessoa.getData());
                    fotoPessoa.setBucket(newFotoPessoa.getBucket());
                    fotoPessoa.setHash(newFotoPessoa.getHash());
                    return fotoPessoaRepository.save(fotoPessoa);
                })
                .orElseThrow(() -> new RuntimeException("Foto não encontrada com o ID: " + id));
    }

    public void delete(Long id) {
        if (fotoPessoaRepository.existsById(id)) {
            fotoPessoaRepository.deleteById(id);
        } else {
            throw new RuntimeException("Foto não encontrada com o ID: " + id);
        }
    }
}
