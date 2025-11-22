package beans;

import entity.Musica;
import entity.Usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.UsuarioRepository;

@Named
@SessionScoped
public class UsuarioBean implements Serializable {
    
    private Usuario usuarioLogado;
    
    @Inject
    private UsuarioRepository usuarioRepository;
    
    private String nome;
    
    private String login;
    private String senha;
    
    private List<Musica> musicasFavoritas;

    public String getNome() {
        return nome;
    }

    public String getLogin() {
        return login;
    }

    public String getSenha() {
        return senha;
    }
    
    public boolean isLogado() {
        return usuarioLogado != null;
    }

    public List<Musica> getMusicasFavoritas() {
        return musicasFavoritas;
    }

    public Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public void setMusicasFavoritas(List<Musica> musicasFavoritas) {
        this.musicasFavoritas = musicasFavoritas;
    }
    
    public String cadastrar() {
        Usuario usuarioNovo = new Usuario(nome, login, senha, false);
        
        usuarioRepository.salvar(usuarioNovo);
        
        return "login.xhtml";
    }
    
    public String logar() {
        usuarioLogado = usuarioRepository.buscar(login, senha);
        
        if(usuarioLogado == null) {
            return "";
        } else {
            return "inicio.xhtml";
        }
    }
    
    public String atualizarNome() {
        Usuario usuarioBanco = 
                usuarioRepository.buscar(usuarioLogado.getLogin(), usuarioLogado.getSenha());
        
        usuarioBanco.setNome(nome);
        
        usuarioRepository.atualizar(usuarioBanco);
        
        usuarioLogado = 
                usuarioRepository.buscar(usuarioLogado.getLogin(), usuarioLogado.getSenha());
        
        return "inicio.xhtml";
    }
    
    
}
