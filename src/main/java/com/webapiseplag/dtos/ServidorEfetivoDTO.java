package com.webapiseplag.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record ServidorEfetivoDTO(
        Long pessoaId,

        @NotBlank(message = "Matrícula é obrigatória")
        @Size(max = 20, message = "Matrícula deve ter no máximo 20 caracteres")
        String matricula
) {}