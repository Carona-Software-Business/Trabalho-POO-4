package beans;

import entity.Musica;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.MusicaRepository;

@Named
@ViewScoped
public class MusicasBean implements Serializable {

    @Inject
    private MusicaRepository musicaRepository;

    private List<Musica> musicas;

    private String nomeMusica;

    private String nomeGenero;

    private String mensagem;

    @PostConstruct
    public void listar() {
        nomeMusica = "";
        nomeGenero = "";
        mensagem = "";
        musicas = musicaRepository.listar();
        if (musicas.isEmpty()) {
            mensagem = "Nenhuma música cadastrada!";
        }
    }

    public void buscaPorNome() {
        musicas = musicaRepository.buscarPorNomeParcial(nomeMusica);
        if (musicas.isEmpty()) {
            mensagem = "Nenhuma música que contém " + nomeMusica + "!";
        } else {
            mensagem = "Mostrando as músicas que contém: \"" + nomeMusica + "\".";
        }
        nomeMusica = "";
    }

    public void buscaPorGenero() {
        musicas = musicaRepository.buscarPorNomeGenero(nomeGenero);
        if (musicas.isEmpty()) {
            mensagem = "Nenhuma música de um gênero que contenha " + nomeGenero + "!";
        } else {
            mensagem = "Mostrando as música de gêneros que contém: \"" + nomeGenero + "\".";
        }
        nomeGenero = "";
    }

    public void removerMusica(Musica musica) {
        musicaRepository.remover(musica);
        listar();
    }

    public String editarMusica(Musica musica) {
        return "edicaoMusica?faces-redirect=true&id=" + musica.getId();
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public void setMusicas(List<Musica> musicas) {
        this.musicas = musicas;
    }

    public String getNomeMusica() {
        return nomeMusica;
    }

    public void setNomeMusica(String nomeMusica) {
        this.nomeMusica = nomeMusica;
    }

    public String getNomeGenero() {
        return nomeGenero;
    }

    public void setNomeGenero(String nomeGenero) {
        this.nomeGenero = nomeGenero;
    }

    public String getMensagem() {
        return mensagem;
    }

}
