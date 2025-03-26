package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity
@Table(name = "lotacao")
public class Lotacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lot_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @ManyToOne
    @JoinColumn(name = "unid_id", nullable = false)
    private Unidade unidade;

    @Column(name = "lot_data_lotacao", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date dataLotacao;

    @Column(name = "lot_data_remocao")
    @Temporal(TemporalType.DATE)
    private Date dataRemocao;

    @Column(name = "lot_portaria", length = 100)
    private String portaria;

    public Lotacao() {
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

    public Unidade getUnidade() {
        return unidade;
    }

    public void setUnidade(Unidade unidade) {
        this.unidade = unidade;
    }

    public Date getDataLotacao() {
        return dataLotacao;
    }

    public void setDataLotacao(Date dataLotacao) {
        this.dataLotacao = dataLotacao;
    }

    public Date getDataRemocao() {
        return dataRemocao;
    }

    public void setDataRemocao(Date dataRemocao) {
        this.dataRemocao = dataRemocao;
    }

    public String getPortaria() {
        return portaria;
    }

    public void setPortaria(String portaria) {
        this.portaria = portaria;
    }
}
