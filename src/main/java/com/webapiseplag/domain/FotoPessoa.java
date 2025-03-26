package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;
import java.util.Date;

@Data
@Entity(name = "foto_pessoa")
@Table(name = "foto_pessoa")
public class FotoPessoa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fp_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "pes_id", nullable = false)
    private Pessoa pessoa;

    @Column(name = "fp_data", nullable = false)
    @Temporal(TemporalType.DATE)
    private Date data;

    @Column(name = "fp_bucket", nullable = false, length = 50)
    private String bucket;

    @Column(name = "fp_hash", nullable = false, length = 50)
    private String hash;

    public FotoPessoa() {
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

    public Date getData() {
        return data;
    }

    public void setData(Date data) {
        this.data = data;
    }

    public String getBucket() {
        return bucket;
    }

    public void setBucket(String bucket) {
        this.bucket = bucket;
    }

    public String getHash() {
        return hash;
    }

    public void setHash(String hash) {
        this.hash = hash;
    }
}