package com.webapiseplag.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

public record ServidorTemporarioDTO(
        @NotNull(message = "ID da pessoa é obrigatório")
        Long pessoaId,

        @NotNull(message = "Data de admissão é obrigatória")
        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataAdmissao,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataDemissao
) {}