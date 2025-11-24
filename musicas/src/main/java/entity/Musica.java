    package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import java.util.ArrayList;
import java.util.List;

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

    @ManyToMany(mappedBy = "musicasFavoritas") 
    private List<Usuario> usuarios = new ArrayList<>();
    
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
    
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Musica)) {
            return false;
        }
        Musica m = (Musica) o;
        return id != 0 && id == m.id;
    }

    @Override
    public int hashCode() {
        return 31;
    }
}
