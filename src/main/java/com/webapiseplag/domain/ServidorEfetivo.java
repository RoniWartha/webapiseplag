package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.io.Serializable;

@Data
@Entity(name = "servidor_efetivo")
@Table(name = "servidor_efetivo")
public class ServidorEfetivo implements Serializable {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "se_matricula", length = 20, nullable = false)
    private String matricula;

    public ServidorEfetivo() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Pessoa getPessoa() {
        return pessoa;
    }

    public void setPessoa(Pessoa pessoa) {
        this.pessoa = pessoa;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }
}
