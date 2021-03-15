package com.example.demo.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

@Entity
@Table(name = "tb_dias_nao_uteis")
public class DiasNaoUteis {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(nullable = false)
    private Date dataNaoUtil;
    private Instant dataCriacao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilizador_Criacao")
    private User userCriacao;

    public DiasNaoUteis(){}

    public DiasNaoUteis(long id, Date dataNaoUtil, Instant dataCriacao, User userCriacao) {
        this.id = id;
        this.dataNaoUtil = dataNaoUtil;
        this.dataCriacao = dataCriacao;
        this.userCriacao = userCriacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Date getDataNaoUtil() {
        return dataNaoUtil;
    }

    public void setDataNaoUtil(Date dataNaoUtil) {
        this.dataNaoUtil = dataNaoUtil;
    }

    public Instant getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Instant dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public User getUserCriacao() {
        return userCriacao;
    }

    public void setUserCriacao(User userCriacao) {
        this.userCriacao = userCriacao;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DiasNaoUteis that = (DiasNaoUteis) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
