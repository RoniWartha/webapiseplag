package com.webapiseplag.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "pessoa_endereco",
        uniqueConstraints = @UniqueConstraint(
                columnNames = {"pes_id", "end_id"},
                name = "uk_pessoa_endereco"
        ))
public class PessoaEndereco {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "pes_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_endereco_pessoa"))
    private Pessoa pessoa;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "end_id", nullable = false, foreignKey = @ForeignKey(name = "fk_pessoa_endereco_endereco"))
    private Endereco endereco;

    // Método para garantir consistência nas operações de associação
    public void associar(Pessoa pessoa, Endereco endereco) {
        this.pessoa = pessoa;
        this.endereco = endereco;
        pessoa.getEnderecos().add(this);
        endereco.getPessoas().add(this);
    }

    public PessoaEndereco() {
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

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }
}
