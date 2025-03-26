package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity(name = "cidade")
@Table(name = "cidade")
public class Cidade {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cid_id")
    private Long id;

    @Column(name = "cid_nome", nullable = false, length = 200)
    private String nome;

    @Column(name = "cid_uf", nullable = false, length = 2)
    private String uf;

    public Cidade() {
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

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }
}