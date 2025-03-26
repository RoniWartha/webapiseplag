package com.webapiseplag.controllers;

import com.webapiseplag.domain.Unidade;
import com.webapiseplag.services.UnidadeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/unidades")
public class UnidadeController {

    @Autowired
    private UnidadeService unidadeService;

    @GetMapping
    public List<Unidade> getAll() {
        return unidadeService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Unidade> findById(@PathVariable Long id) {
        return unidadeService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Unidade create(@RequestBody Unidade unidade) {
        return unidadeService.create(unidade);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Unidade> updateById(@PathVariable Long id, @RequestBody Unidade unidade) {
        try {
            Unidade newUnidade = unidadeService.update(id, unidade);
            return ResponseEntity.ok(newUnidade);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            unidadeService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}