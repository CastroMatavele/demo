package com.example.demo.model;
import javax.persistence.*;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "tb_categorias")
public class Categoria {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private  long id;
    @Column(name = "nome")
    private String nome;
    @Column(name = "situacao")
    private boolean situacao;
    @Column(name = "dataCriacao", nullable = false)
    private Timestamp dataCriacao;
    @Column(name = "categoria_image")

    @OneToMany(mappedBy = "categoria", cascade = {
            CascadeType.ALL
    })
    private List<Cortes> cortesList;
    @OneToOne
    @MapsId
    private Image image;
    public Categoria(){
    }

    public Categoria(long id, String nome, boolean situacao, Timestamp dataCriacao, Image image) {
        this.id = id;
        this.nome = nome;
        this.situacao = situacao;
        this.dataCriacao = dataCriacao;
        this.image = image;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean isSituacao() {
        return situacao;
    }

    public void setSituacao(boolean situacao) {
        this.situacao = situacao;
    }

    public Timestamp getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(Timestamp dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public List<Cortes> getCortesList() {
        return cortesList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Categoria categoria = (Categoria) o;
        return id == categoria.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
