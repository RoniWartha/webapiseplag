package com.webapiseplag.dtos;

public record UnidadeEnderecoResponseDTO(
        Long id,
        Long unidadeId,
        String unidadeNome,
        Long enderecoId,
        String enderecoCompleto
) {}
