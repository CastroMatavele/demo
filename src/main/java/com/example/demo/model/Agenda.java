package com.example.demo.model;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_agenda")
public class Agenda {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Timestamp dataCriacao;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "corte_id",nullable = false)
    public Cortes cortes;
    private Date dataCorte;
    private Time HoraCorte;
    private boolean situacaoCorte;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilizador_id")
    public User utilizador;

    public Agenda (){

    }

    public Agenda(Long id, Timestamp dataCriacao, Cortes cortes, Date dataCorte, Time horaCorte, boolean situacaoCorte, User utilizador) {
        this.id = id;
        this.dataCriacao = dataCriacao;
        this.cortes = cortes;
        this.dataCorte = dataCorte;
        HoraCorte = horaCorte;
        this.situacaoCorte = situacaoCorte;
        this.utilizador = utilizador;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Cortes getCortes() {
        return cortes;
    }

    public void setCortes(Cortes cortes) {
        this.cortes = cortes;
    }

    public Date getDataCorte() {
        return dataCorte;
    }

    public void setDataCorte(Date dataCorte) {
        this.dataCorte = dataCorte;
    }

    public Time getHoraCorte() {
        return HoraCorte;
    }

    public void setHoraCorte(Time horaCorte) {
        HoraCorte = horaCorte;
    }

    public boolean isSituacaoCorte() {
        return situacaoCorte;
    }

    public void setSituacaoCorte(boolean situacaoCorte) {
        this.situacaoCorte = situacaoCorte;
    }

    public User getUtilizador() {
        return utilizador;
    }

    public void setUtilizador(User utilizador) {
        this.utilizador = utilizador;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Agenda agenda = (Agenda) o;
        return Objects.equals(id, agenda.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
