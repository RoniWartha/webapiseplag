package com.webapiseplag.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public record LotacaoResponseDTO(
        Long id,
        Long pessoaId,
        String pessoaNome,
        Long unidadeId,
        String unidadeNome,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataLotacao,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataRemocao,
        String portaria
) {}
