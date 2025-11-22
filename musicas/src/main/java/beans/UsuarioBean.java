package beans;

import entity.Musica;
import entity.Usuario;
import jakarta.enterprise.context.SessionScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
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
    
    private String novaSenha;
    
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

    public String getNovaSenha() {
        return novaSenha;
    }

    public void setNovaSenha(String novaSenha) {
        this.novaSenha = novaSenha;
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
        if(login.length() > 32 || senha.length() > 32) {
            mensagemErroTamanho();
            return "";
        }
        
        Usuario usuarioNovo = new Usuario(nome, login, senha, false);
        
        usuarioRepository.salvar(usuarioNovo);
        
        return "login.xhtml";
    }
    
    public String logar() {
        usuarioLogado = usuarioRepository.buscar(login, senha);
        
        if(usuarioLogado == null) {
            mensagemErroLogin();
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
    
    public String atualizarSenha() {
        if(senha.equals(usuarioLogado.getSenha())) {
            
            if(novaSenha.length() > 32) {
                mensagemErroTamanho();
                return "";
            }
            
            Usuario usuarioBanco = 
                    usuarioRepository.buscar(usuarioLogado.getLogin(), usuarioLogado.getSenha());
            
            usuarioBanco.setSenha(novaSenha);
            
            usuarioRepository.atualizar(usuarioBanco);
            
            usuarioLogado = 
                usuarioRepository.buscar(usuarioLogado.getLogin(), novaSenha);
        } else {
            mensagemErroNovaSenha();
            return "";
        }
        
        return "inicio.xhtml";
    }
    
    private void mensagemErroTamanho() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erro!", "O login e a senha devem ter no máximo 32 caracteres");
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void mensagemErroLogin() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erro de login", "Login ou senha incorretos");
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    private void mensagemErroNovaSenha() {
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, 
                "Erro!", "A senha digita está errada!");
        
        FacesContext.getCurrentInstance().addMessage(null, msg);
    }
    
    
}
