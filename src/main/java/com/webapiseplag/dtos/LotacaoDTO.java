package com.webapiseplag.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import java.util.Date;

public record LotacaoDTO(
        @NotNull(message = "ID da pessoa é obrigatório")
        Long pessoaId,

        @NotNull(message = "ID da unidade é obrigatório")
        Long unidadeId,

        @NotNull(message = "Data de lotação é obrigatória")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataLotacao,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataRemocao,

        @Size(max = 100, message = "Portaria deve ter no máximo 100 caracteres")
        String portaria
) {}