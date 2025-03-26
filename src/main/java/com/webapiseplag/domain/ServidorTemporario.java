package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity(name = "servidor_temporario")
@Table(name = "servidor_temporario")
public class ServidorTemporario {

    @Id
    @Column(name = "pes_id")
    private Long id;

    @OneToOne
    @MapsId
    @JoinColumn(name = "pes_id")
    private Pessoa pessoa;

    @Column(name = "st_data_admissao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataAdmissao;

    @Column(name = "st_data_demissao")
    @Temporal(TemporalType.DATE)
    private Date dataDemissao;

    public ServidorTemporario() {
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

    public Date getDataAdmissao() {
        return dataAdmissao;
    }

    public void setDataAdmissao(Date dataAdmissao) {
        this.dataAdmissao = dataAdmissao;
    }

    public Date getDataDemissao() {
        return dataDemissao;
    }

    public void setDataDemissao(Date dataDemissao) {
        this.dataDemissao = dataDemissao;
    }
}