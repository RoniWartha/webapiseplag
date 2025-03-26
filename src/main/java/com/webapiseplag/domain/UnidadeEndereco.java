package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "unidade_endereco",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"unid_id", "end_id"},
                name = "uk_unidade_endereco"
        ))
public class UnidadeEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;

    @ManyToOne
    @JoinColumn(name = "end_id", nullable = false)
    private Endereco endereco;

    public UnidadeEndereco() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}