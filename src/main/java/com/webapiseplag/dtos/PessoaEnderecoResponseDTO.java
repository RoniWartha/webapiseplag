package com.webapiseplag.dtos;

public record PessoaEnderecoResponseDTO(
        Long id,
        Long pessoaId,
        Long enderecoId,
        String pessoaNome,
        String enderecoLogradouro
) {}
