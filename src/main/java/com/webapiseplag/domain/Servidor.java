package com.webapiseplag.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
public class Servidor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private Integer idade;
    private String fotografia;

    @ManyToOne
    @JoinColumn(name = "unid_id")
    private Unidade unidade;

    public Servidor(Long id, String nome, Integer idade, String fotografia, Unidade unidade) {
        this.id = id;
        this.nome = nome;
        this.idade = idade;
        this.fotografia = fotografia;
        this.unidade = unidade;
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

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getFotografia() {
        return fotografia;
    }

    public void setFotografia(String fotografia) {
        this.fotografia = fotografia;
    }

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }
}
