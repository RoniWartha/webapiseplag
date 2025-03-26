package com.webapiseplag.controllers;

import com.webapiseplag.dtos.ServidorTemporarioDTO;
import com.webapiseplag.dtos.ServidorTemporarioResponseDTO;
import com.webapiseplag.services.ServidorTemporarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/servidores-temporarios")
public class ServidorTemporarioController {

    private final ServidorTemporarioService servidorTemporarioService;

    @Autowired
    public ServidorTemporarioController(ServidorTemporarioService servidorTemporarioService) {
        this.servidorTemporarioService = servidorTemporarioService;
    }

    @GetMapping
    public ResponseEntity<List<ServidorTemporarioResponseDTO>> getAll() {
        List<ServidorTemporarioResponseDTO> servidores = servidorTemporarioService.findAll();
        return ResponseEntity.ok(servidores);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> getById(@PathVariable Long id) {
        ServidorTemporarioResponseDTO servidor = servidorTemporarioService.findById(id);
        return ResponseEntity.ok(servidor);
    }

    @PostMapping
    public ResponseEntity<ServidorTemporarioResponseDTO> create(
            @Valid @RequestBody ServidorTemporarioDTO servidorDTO) {
        ServidorTemporarioResponseDTO novoServidor = servidorTemporarioService.create(servidorDTO);
        return new ResponseEntity<>(novoServidor, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServidorTemporarioResponseDTO> update(
            @PathVariable Long id,
            @Valid @RequestBody ServidorTemporarioDTO servidorDTO) {
        ServidorTemporarioResponseDTO servidorAtualizado = servidorTemporarioService.update(id, servidorDTO);
        return ResponseEntity.ok(servidorAtualizado);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        servidorTemporarioService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/exists/pessoa/{pessoaId}")
    public ResponseEntity<Boolean> existsByPessoaId(@PathVariable Long pessoaId) {
        boolean exists = servidorTemporarioService.existsByPessoaId(pessoaId);
        return ResponseEntity.ok(exists);
    }
}