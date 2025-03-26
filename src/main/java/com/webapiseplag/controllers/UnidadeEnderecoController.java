package com.webapiseplag.controllers;

import com.webapiseplag.dtos.UnidadeEnderecoDTO;
import com.webapiseplag.dtos.UnidadeEnderecoResponseDTO;
import com.webapiseplag.services.UnidadeEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/unidades-enderecos")
public class UnidadeEnderecoController {

    private final UnidadeEnderecoService unidadeEnderecoService;

    @Autowired
    public UnidadeEnderecoController(UnidadeEnderecoService unidadeEnderecoService) {
        this.unidadeEnderecoService = unidadeEnderecoService;
    }

    @GetMapping
    public ResponseEntity<List<UnidadeEnderecoResponseDTO>> findAll() {
        return ResponseEntity.ok(unidadeEnderecoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UnidadeEnderecoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(unidadeEnderecoService.findById(id));
    }

    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<UnidadeEnderecoResponseDTO>> findByUnidadeId(@PathVariable Long unidadeId) {
        return ResponseEntity.ok(unidadeEnderecoService.findByUnidadeId(unidadeId));
    }

    @GetMapping("/endereco/{enderecoId}")
    public ResponseEntity<List<UnidadeEnderecoResponseDTO>> findByEnderecoId(@PathVariable Long enderecoId) {
        return ResponseEntity.ok(unidadeEnderecoService.findByEnderecoId(enderecoId));
    }

    @PostMapping
    public ResponseEntity<UnidadeEnderecoResponseDTO> create(@RequestBody UnidadeEnderecoDTO dto) {
        UnidadeEnderecoResponseDTO response = unidadeEnderecoService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        unidadeEnderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByUnidadeAndEndereco(
            @RequestParam Long unidadeId,
            @RequestParam Long enderecoId) {
        unidadeEnderecoService.deleteByUnidadeAndEndereco(unidadeId, enderecoId);
        return ResponseEntity.noContent().build();
    }
}
