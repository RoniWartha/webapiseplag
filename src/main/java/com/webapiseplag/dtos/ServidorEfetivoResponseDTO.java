package com.webapiseplag.dtos;

public record ServidorEfetivoResponseDTO(
        Long id,
        Long pessoaId,
        String pessoaNome,
        String matricula
) {}