package com.example.demo.model;

import javax.persistence.*;
import java.sql.Time;
import java.util.Objects;
@Entity
@Table(name = "tb_horasInvalidas")
public class HorasInvalidas {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Time horaInvalida;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "utilizadorCriacao")
    private User user;

    public HorasInvalidas(){}

    public HorasInvalidas(long id, Time horaInvalida, User user) {
        this.id = id;
        this.horaInvalida = horaInvalida;
        this.user = user;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Time getHoraInvalida() {
        return horaInvalida;
    }

    public void setHoraInvalida(Time horaInvalida) {
        this.horaInvalida = horaInvalida;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        HorasInvalidas that = (HorasInvalidas) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
