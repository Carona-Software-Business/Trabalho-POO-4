package beans;

import entity.Genero;
import jakarta.annotation.PostConstruct;
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

    private String nomeBusca;

    private String mensagem;

    @PostConstruct
    public void listar() {
        generos = generoRepository.listar();
        if (generos.isEmpty()) {
            mensagem = "Nenhum gênero encontrado!";
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
        generoRepository.remover(genero);
        listar();
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

}
