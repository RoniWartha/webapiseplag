package com.webapiseplag.controllers;

import com.webapiseplag.domain.FotoPessoa;
import com.webapiseplag.services.FotoPessoaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/fotos-pessoa")
public class FotoPessoaController {

    @Autowired
    private FotoPessoaService fotoPessoaService;

    @GetMapping
    public List<FotoPessoa> getAll() {
        return fotoPessoaService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FotoPessoa> findById(@PathVariable Long id) {
        return fotoPessoaService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public FotoPessoa create(@RequestBody FotoPessoa fotoPessoa) {
        return fotoPessoaService.create(fotoPessoa);
    }

    @PutMapping("/{id}")
    public ResponseEntity<FotoPessoa> updateById(@PathVariable Long id, @RequestBody FotoPessoa fotoPessoa) {
        try {
            FotoPessoa newFotoPessoa = fotoPessoaService.update(id, fotoPessoa);
            return ResponseEntity.ok(newFotoPessoa);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            fotoPessoaService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}