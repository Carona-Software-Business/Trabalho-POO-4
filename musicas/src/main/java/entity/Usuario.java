package entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import java.util.List;

@Entity
@Table(name = "USUARIO")
public class Usuario {
    
    @Id
    @GeneratedValue
    private long id;
    
    private String nome;
    
    @Column(unique = true, nullable = false)
    private String login;
    
    @Column(nullable = false)
    private String senha;
    
    private boolean administrador;
    
    @ManyToMany
    private List<Musica> musicasFavoritas;
    
    public Usuario(){};

    public Usuario(String nome, String login, String senha, boolean isAdm) {
        this.nome = nome;
        this.login = login;
        this.senha = senha;
        this.administrador = isAdm;
    }

    public long getId() {
        return id;
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
