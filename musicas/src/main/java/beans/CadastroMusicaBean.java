package beans;

import entity.Genero;
import entity.Musica;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.List;
import repository.GeneroRepository;
import repository.MusicaRepository;

@Named
@RequestScoped
public class CadastroMusicaBean {

    @Inject
    private MusicaRepository musicaRepository;

    @Inject
    private GeneroRepository generoRepository;

    private List<Genero> generos;

    private long idGeneroSelecionado;

    private Musica musica = new Musica();

    private String mensagem;

    @PostConstruct
    public void inicializar() {
        generos = generoRepository.listar();
    }

    public void cadastrarMusica() {
        try {
            Genero genero = generoRepository.buscarPorID(idGeneroSelecionado);
            musica.setGenero(genero);
            musicaRepository.salvar(musica);
            mensagem = "Música cadastrada com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar a música!";
        } finally {
            musica = new Musica();
        }
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

    public List<Genero> getGeneros() {
        return generos;
    }

    public long getIdGeneroSelecionado() {
        return idGeneroSelecionado;
    }

    public void setIdGeneroSelecionado(long idGeneroSelecionado) {
        this.idGeneroSelecionado = idGeneroSelecionado;
    }

}
