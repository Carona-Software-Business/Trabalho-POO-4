package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import java.util.List;

@Entity
public class Genero {

    @Id
    @GeneratedValue
    private long id;

    @Column(unique = true)
    private String nome;
    
    @OneToMany(mappedBy = "genero")
    private List<Musica> musicas;

    public Genero() {
    }

    public Genero(String nome) {
        this.nome = nome;
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

    @Override
    public String toString() {
        return nome;
    }

    public List<Musica> getMusicas() {
        return musicas;
    }
}
