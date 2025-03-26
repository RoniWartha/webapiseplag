package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "unidade")
@Table(name = "unidade")
public class Unidade {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "unid_id")
    private Long id;

    @Column(name = "unid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "unid_sigla", length = 20)
    private String sigla;

    public Unidade() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSigla() {
        return sigla;
    }

    public void setSigla(String sigla) {
        this.sigla = sigla;
    }
}