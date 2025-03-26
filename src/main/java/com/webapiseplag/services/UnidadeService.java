package com.webapiseplag.services;

import com.webapiseplag.domain.Unidade;
import com.webapiseplag.repositories.UnidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UnidadeService {

    @Autowired
    private UnidadeRepository unidadeRepository;

    public List<Unidade> getAll() {
        return unidadeRepository.findAll();
    }

    public Optional<Unidade> findById(Long id) {
        return unidadeRepository.findById(id);
    }

    public Unidade create(Unidade unidade) {
        return unidadeRepository.save(unidade);
    }

    public Unidade update(Long id, Unidade newUnidade) {
        return unidadeRepository.findById(id)
                .map(unidade -> {
                    unidade.setNome(newUnidade.getNome());
                    unidade.setSigla(newUnidade.getSigla());
                    return unidadeRepository.save(unidade);
                })
                .orElseThrow(() -> new RuntimeException("Unidade não encontrada com o ID: " + id));
    }

    public void delete(Long id) {
        if (unidadeRepository.existsById(id)) {
            unidadeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Unidade não encontrada com o ID: " + id);
        }
    }
}