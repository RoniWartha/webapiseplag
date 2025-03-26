package com.webapiseplag.controllers;

import com.webapiseplag.domain.Endereco;
import com.webapiseplag.services.EnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @GetMapping
    public List<Endereco> getAll() {
        return enderecoService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Endereco> findById(@PathVariable Long id) {
        return enderecoService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public Endereco create(@RequestBody Endereco endereco) {
        return enderecoService.create(endereco);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Endereco> updateById(@PathVariable Long id, @RequestBody Endereco endereco) {
        try {
            Endereco newEndereco = enderecoService.update(id, endereco);
            return ResponseEntity.ok(newEndereco);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        try {
            enderecoService.delete(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
