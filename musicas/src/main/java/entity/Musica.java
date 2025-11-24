    package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

@Entity
public class Musica {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String nome;

    @ManyToOne
    private Genero genero;
    
    @ManyToOne
    private Banda banda;

    public Musica() {
    }

    public Musica(String nome, Genero genero, Banda banda) {
        this.nome = nome;
        this.genero = genero;
        this.banda = banda;
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

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }
}
