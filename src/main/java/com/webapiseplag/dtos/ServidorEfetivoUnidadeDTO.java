package com.webapiseplag.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ServidorEfetivoUnidadeDTO {
    private String nome;
    private int idade;
    private String unidadeLotacao;
    private String fotografia;
}

