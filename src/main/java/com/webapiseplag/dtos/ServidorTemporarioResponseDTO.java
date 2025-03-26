package com.webapiseplag.dtos;

import com.fasterxml.jackson.annotation.JsonFormat;
import java.util.Date;

public record ServidorTemporarioResponseDTO(
        Long id,
        Long pessoaId,
        String pessoaNome,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataAdmissao,

        @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
        Date dataDemissao
) {}
