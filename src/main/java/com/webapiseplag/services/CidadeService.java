package com.webapiseplag.services;

import com.webapiseplag.domain.Cidade;
import com.webapiseplag.repositories.CidadeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class CidadeService {

    @Autowired
    private CidadeRepository cidadeRepository;

    public List<Cidade> getAll() {
        return cidadeRepository.findAll();
    }

    public Optional<Cidade> findById(Long id) {
        return cidadeRepository.findById(id);
    }

    public Cidade create(Cidade cidade) {
        return cidadeRepository.save(cidade);
    }

    public Cidade update(Long id, Cidade newCidade) {
        return cidadeRepository.findById(id)
                .map(cidade -> {
                    cidade.setNome(newCidade.getNome());
                    cidade.setUf(newCidade.getUf());
                    return cidadeRepository.save(cidade);
                })
                .orElseThrow(() -> new RuntimeException("Cidade não encontrada com o ID: " + id));
    }

    public void delete(Long id) {
        if (cidadeRepository.existsById(id)) {
            cidadeRepository.deleteById(id);
        } else {
            throw new RuntimeException("Cidade não encontrada com o ID: " + id);
        }
    }
}
