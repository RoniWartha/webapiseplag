package com.webapiseplag.controllers;

import com.webapiseplag.dtos.LotacaoDTO;
import com.webapiseplag.dtos.LotacaoResponseDTO;
import com.webapiseplag.services.LotacaoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/lotacoes")
public class LotacaoController {

    private final LotacaoService lotacaoService;

    @Autowired
    public LotacaoController(LotacaoService lotacaoService) {
        this.lotacaoService = lotacaoService;
    }

    @GetMapping
    public ResponseEntity<List<LotacaoResponseDTO>> findAll() {
        return ResponseEntity.ok(lotacaoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LotacaoResponseDTO> findById(@PathVariable Long id) {
        return ResponseEntity.ok(lotacaoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<LotacaoResponseDTO> create(@Valid @RequestBody LotacaoDTO dto) {
        LotacaoResponseDTO response = lotacaoService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LotacaoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody LotacaoDTO dto) {
        return ResponseEntity.ok(lotacaoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        lotacaoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/pessoa/{pessoaId}")
    public ResponseEntity<List<LotacaoResponseDTO>> findByPessoaId(@PathVariable Long pessoaId) {
        return ResponseEntity.ok(lotacaoService.findByPessoaId(pessoaId));
    }

    @GetMapping("/unidade/{unidadeId}")
    public ResponseEntity<List<LotacaoResponseDTO>> findByUnidadeId(@PathVariable Long unidadeId) {
        return ResponseEntity.ok(lotacaoService.findByUnidadeId(unidadeId));
    }
}
