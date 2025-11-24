package beans;

import entity.Genero;
import entity.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.GeneroRepository;

@Named
@ViewScoped
public class GenerosBean implements Serializable {

    @Inject
    private GeneroRepository generoRepository;

    private List<Genero> generos;
    
    private String nomeGenero;

    private String nomeBusca;

    private String mensagem;
    
    @Inject
    private UsuarioBean usuarioBean;
    
    private Genero generoSelecionado;

    @PostConstruct
    public void init() {
        listar();

        String nomeParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("nomeGenero");

        if (nomeParam != null && !nomeParam.isEmpty()) {
            try {
                generoSelecionado = generoRepository.buscarPorNome(nomeParam);
            } catch (Exception e) {
                mensagem = "Gênero não encontrado: " + nomeParam;
            }
        }
    }

    public void buscarPorNomeParcial() {
        generos = generoRepository.buscarPorNomeParcial(nomeBusca);
        if (generos.isEmpty()) {
            mensagem = "Nenhum gênero encontrado com nome " + nomeBusca + "!";
        } else {
            mensagem = "Mostrando os gêneros que contém: \"" + nomeBusca + "\".";
        }
        nomeBusca = "";
    }

    public void removerGenero(Genero genero) {
        if (!usuarioBean.getUsuarioLogado().isAdministrador()) {
            mensagem = "Apenas administradores podem remover gêneros.";
            return;
        }
        if (genero.getMusicas() != null && !genero.getMusicas().isEmpty()) {
            mensagem = "Não é possível remover: existem músicas cadastradas nesse gênero.";
            return;
        }
        generoRepository.remover(genero);
        listar();
        mensagem = "Gênero removido com sucesso!";
    }
    
    public String editarGenero(Genero genero) {
        this.generoSelecionado = genero;
        return "editarGenero?faces-redirect=true&nomeGenero=" + genero.getNome();
    }
    
    public void salvarEdicao() {
        try {
            generoRepository.salvar(generoSelecionado);
            mensagem = "Nome do gênero atualizado com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao atualizar gênero!";
        }
    }
    
    public String cadastrar() {
        Genero novoGenero = new Genero(nomeGenero);
        
        try {
            generoRepository.salvar(novoGenero);
            
            mensagem = "Genero cadastrado com sucesso";
        } catch(Exception ex) {
            System.out.println("Erro ao cadastrar o genero");
            System.out.println(ex.getMessage());
            
            mensagem = "Não foi possível cadastrar o gênero";
        } finally {
            return "";
        }
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public String getNomeBusca() {
        return nomeBusca;
    }

    public void setNomeBusca(String nomeBusca) {
        this.nomeBusca = nomeBusca;
    }

    public String getMensagem() {
        return mensagem;
    }
    
    public String selecionarGenero(Genero genero){
        return "musicasPorGenero?faces-redirect=true&nomeGenero=" + genero.getNome();
    }
    
    public Genero getGeneroSelecionado() {
        return generoSelecionado;
    }
    
    public void listar() {
        generos = generoRepository.listar();
        if (generos.isEmpty()) {
            mensagem = "Nenhum gênero encontrado!";
        }
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

}
