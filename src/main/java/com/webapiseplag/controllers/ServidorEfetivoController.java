package com.webapiseplag.controllers;

import com.webapiseplag.domain.Servidor;
import com.webapiseplag.dtos.EnderecoFuncionalDTO;
import com.webapiseplag.dtos.ServidorEfetivoDTO;
import com.webapiseplag.dtos.ServidorEfetivoResponseDTO;
import com.webapiseplag.dtos.ServidorEfetivoUnidadeDTO;
import com.webapiseplag.services.ServidorEfetivoService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores-efetivos")
public class ServidorEfetivoController {

    private final ServidorEfetivoService servidorEfetivoService;

    @Autowired
    public ServidorEfetivoController(ServidorEfetivoService servidorEfetivoService) {
        this.servidorEfetivoService = servidorEfetivoService;
    }

    @GetMapping
    public ResponseEntity<List<ServidorEfetivoResponseDTO>> getAll() {
        return ResponseEntity.ok(servidorEfetivoService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> getById(@PathVariable Long id) {
        return ResponseEntity.ok(servidorEfetivoService.findById(id));
    }

    @PostMapping
    public ResponseEntity<ServidorEfetivoResponseDTO> create(
            @Valid @RequestBody ServidorEfetivoDTO dto) {
        ServidorEfetivoResponseDTO response = servidorEfetivoService.create(dto);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorEfetivoResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ServidorEfetivoDTO dto) {
        return ResponseEntity.ok(servidorEfetivoService.update(id, dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servidorEfetivoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/unidade/{unidId}")
    public ResponseEntity<List<ServidorEfetivoUnidadeDTO>> getByUnidadeId(@PathVariable Long unidId) {
        return ResponseEntity.ok(servidorEfetivoService.findByUnidadeId(unidId));
    }

    @GetMapping("/buscar-endereco")
    public List<EnderecoFuncionalDTO> buscarEnderecoFuncional(@RequestParam String nome) {
        return servidorEfetivoService.buscarEnderecoFuncionalPorNome(nome);
    }
}
