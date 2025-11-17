package beans;

import entity.Genero;
import entity.Musica;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.GeneroRepository;
import repository.MusicaRepository;

@Named
@ViewScoped
public class EdicaoMusicaBean implements Serializable {

    @Inject
    private MusicaRepository musicaRepository;

    @Inject
    private GeneroRepository generoRepository;

    private List<Genero> generos;

    private long idMusica;

    private long idGeneroSelecionado;

    private Musica musica;

    private String mensagem;

    @PostConstruct
    public void inicializar() {
        generos = generoRepository.listar();
    }

    public void carregarMusica() {
        musica = musicaRepository.buscarPorID(idMusica);
        idGeneroSelecionado = musica.getGenero().getId();
    }

    public void editarMusica() {
        try {
            Genero novoGenero = generoRepository.buscarPorID(idGeneroSelecionado);
            musica.setGenero(novoGenero);
            musicaRepository.atualizar(musica);
            mensagem = "Música atualizada com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao atualizar a música!";
        }
    }

    public List<Genero> getGeneros() {
        return generos;
    }

    public void setGeneros(List<Genero> generos) {
        this.generos = generos;
    }

    public long getIdGeneroSelecionado() {
        return idGeneroSelecionado;
    }

    public void setIdGeneroSelecionado(long idGeneroSelecionado) {
        this.idGeneroSelecionado = idGeneroSelecionado;
    }

    public Musica getMusica() {
        return musica;
    }

    public void setMusica(Musica musica) {
        this.musica = musica;
    }

    public String getMensagem() {
        return mensagem;
    }

    public long getIdMusica() {
        return idMusica;
    }

    public void setIdMusica(long idMusica) {
        this.idMusica = idMusica;
    }

}
