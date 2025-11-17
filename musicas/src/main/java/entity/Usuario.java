package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import java.util.List;

@Entity
public class Usuario {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String nome;
    
    @Column(unique = true)
    private String login;
    
    private String senha;
    
    private boolean administrador;
    
    private List<Musica> musicasFavoritas;

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

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public boolean isAdministrador() {
        return administrador;
    }

    public void setAdministrador(boolean administrador) {
        this.administrador = administrador;
    }

    public List<Musica> getMusicasFavoritas() {
        return musicasFavoritas;
    }

    public void setMusicasFavoritas(List<Musica> musicasFavoritas) {
        this.musicasFavoritas = musicasFavoritas;
    }

    @Override
    public String toString() {
        String msg = "Usuario: " + nome + " - Id: " + id;
        
        if(administrador)
            msg += " - Usuario administrador";
        
        return msg;
    }
    
    
}
