package com.webapiseplag.controllers;

import com.webapiseplag.dtos.PessoaEnderecoResponseDTO;
import com.webapiseplag.services.PessoaEnderecoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/pessoas-enderecos")
public class PessoaEnderecoController {

    private final PessoaEnderecoService pessoaEnderecoService;

    @Autowired
    public PessoaEnderecoController(PessoaEnderecoService pessoaEnderecoService) {
        this.pessoaEnderecoService = pessoaEnderecoService;
    }

    @GetMapping
    public ResponseEntity<List<PessoaEnderecoResponseDTO>> getAll() {
        List<PessoaEnderecoResponseDTO> relacionamentos = pessoaEnderecoService.findAll();
        return ResponseEntity.ok(relacionamentos);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PessoaEnderecoResponseDTO> getById(@PathVariable Long id) {
        PessoaEnderecoResponseDTO relacionamento = pessoaEnderecoService.findById(id);
        return ResponseEntity.ok(relacionamento);
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<PessoaEnderecoResponseDTO>> getByPessoaId(@PathVariable Long pessoaId) {
        List<PessoaEnderecoResponseDTO> relacionamentos = pessoaEnderecoService.findByPessoaId(pessoaId);
        return ResponseEntity.ok(relacionamentos);
    }

    @GetMapping("/endereco/{enderecoId}")
    public ResponseEntity<List<PessoaEnderecoResponseDTO>> getByEnderecoId(@PathVariable Long enderecoId) {
        List<PessoaEnderecoResponseDTO> relacionamentos = pessoaEnderecoService.findByEnderecoId(enderecoId);
        return ResponseEntity.ok(relacionamentos);
    }

    @PostMapping
    public ResponseEntity<PessoaEnderecoResponseDTO> create(
            @RequestParam Long pessoaId,
            @RequestParam Long enderecoId) {
        PessoaEnderecoResponseDTO novoRelacionamento = pessoaEnderecoService.create(pessoaId, enderecoId);
        return new ResponseEntity<>(novoRelacionamento, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        pessoaEnderecoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteByPessoaAndEndereco(
            @RequestParam Long pessoaId,
            @RequestParam Long enderecoId) {
        pessoaEnderecoService.deleteByPessoaAndEndereco(pessoaId, enderecoId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/pessoa/{pessoaId}")
    public ResponseEntity<Boolean> pessoaHasAnyEndereco(@PathVariable Long pessoaId) {
        boolean exists = pessoaEnderecoService.pessoaHasAnyEndereco(pessoaId);
        return ResponseEntity.ok(exists);
    }

    @GetMapping("/exists/endereco/{enderecoId}")
    public ResponseEntity<Boolean> enderecoHasAnyPessoa(@PathVariable Long enderecoId) {
        boolean exists = pessoaEnderecoService.enderecoHasAnyPessoa(enderecoId);
        return ResponseEntity.ok(exists);
    }
}
