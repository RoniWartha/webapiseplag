package com.webapiseplag.controllers;

import com.webapiseplag.domain.Cidade;
import com.webapiseplag.services.CidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @GetMapping
    public List<Cidade> getAll() {
        return cidadeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cidade> findById(@PathVariable Long id) {
        return cidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Cidade create(@RequestBody Cidade cidade) {
        return cidadeService.create(cidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Cidade> updateById(@PathVariable Long id, @RequestBody Cidade cidade) {
        try {
            Cidade newCidade = cidadeService.update(id, cidade);
            return ResponseEntity.ok(newCidade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            cidadeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
