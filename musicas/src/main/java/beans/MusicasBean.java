package beans;

import entity.Banda;
import entity.Genero;
import entity.Musica;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import repository.BandaRepository;
import repository.GeneroRepository;
import repository.MusicaRepository;

@Named
@ViewScoped
public class MusicasBean implements Serializable {

    @Inject
    private MusicaRepository musicaRepository;
    
    @Inject
    private BandaRepository bandaRepository;
    
    @Inject
    private GeneroRepository generoRepository;

    @Inject
    private UsuarioBean usuarioBean;
    
    private List<Musica> musicas;
    
    private long generoId;
    
    private long bandaId;

    private String nomeMusica;
    
    private String nomeBanda;

    private String nomeGenero;

    private String mensagem;
    
    private Musica musicaSelecionada;
    
    private long id;

    @PostConstruct
    public void listar() {
        musicas = musicaRepository.listar();
        if (musicas.isEmpty()) {
            mensagem = "Nenhuma música cadastrada!";
            musicas = new ArrayList<>();
        }
        
        String idParam = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap()
            .get("id");

        if (idParam != null) {
            try {
                id = Long.parseLong(idParam);
                musicaSelecionada = musicaRepository.buscarPorID(id);
            } catch (NumberFormatException e) {
                mensagem = "ID inválido para edição.";
            }
        } else {
            System.out.println("A musica é null");
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
    
    public void buscaPorBanda() {
        musicas = musicaRepository.buscarPorNomeBanda(nomeBanda);
        if (musicas.isEmpty()) {
            mensagem = "Nenhuma música de uma banda que contenha " + nomeBanda + "!";
        } else {
            mensagem = "Mostrando as música de uma banda que contém: \"" + nomeBanda + "\".";
        }
        nomeBanda = "";
    }
    
    public String cadastrar() {
        Banda banda = bandaRepository.buscarPorID(bandaId);
        
        Genero genero = generoRepository.buscarPorID(generoId);
        
        Musica novaMusica = new Musica(nomeMusica, genero, banda);
        
        try {
            musicaRepository.salvar(novaMusica);
        
            mensagem = "Música cadastrada com sucesso"; 
        } catch(Exception ex) {
            System.out.println("Erro ao cadastrar musica");
            System.out.println(ex.getMessage());
            
            mensagem = "Não foi possível cadastrar a música";
        } finally {
            return "";
        }
    }
    
    public String salvarEdicao() {
        Banda novaBanda = bandaRepository.buscarPorID(bandaId);
        
        Genero novoGenero = generoRepository.buscarPorID(generoId);
        
        musicaSelecionada.setNome(nomeMusica);
        musicaSelecionada.setBanda(novaBanda);
        musicaSelecionada.setGenero(novoGenero);
        
        try {
            musicaRepository.salvar(musicaSelecionada);
            
            mensagem = "Música editada com sucesso!";
            
        } catch (Exception ex) {
            System.out.println("Erro ao editar musica");
            System.out.println(ex.getMessage());
            mensagem = "Não foi possível cadastrar a música!";
        } finally {
            return "";
        }
    }

    public void removerMusica(Musica musica) {

    if (musicaRepository.musicaTemFavoritos(musica.getId())) {
        mensagem = "A música não pode ser removida porque está nos favoritos de usuários!";
        return;
    }

    try {
        musicaRepository.remover(musica);
        mensagem = "Música removida com sucesso!";
    } catch (Exception ex) {
        mensagem = "Erro inesperado ao remover a música.";
    } finally {
        listar();
    }
}

    public String editarMusica(Musica musica) {
        System.out.println(musica);
        System.out.println("Id: " + musica.getId());
        this.musicaSelecionada = musica;
        return "edicaoMusica?faces-redirect=true&id=" + musica.getId();
    }
    
    public void favoritar(Musica musica) {
        usuarioBean.favoritar(musica);
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

    public String getNomeBanda() {
        return nomeBanda;
    }

    public void setNomeBanda(String nomeBanda) {
        this.nomeBanda = nomeBanda;
    }
    
    public String getMensagem() {
        return mensagem;
    }

    public long getGeneroId() {
        return generoId;
    }

    public void setGeneroId(long generoId) {
        this.generoId = generoId;
    }

    public long getBandaId() {
        return bandaId;
    }

    public void setBandaId(long bandaId) {
        this.bandaId = bandaId;
    }

    public Musica getMusicaSelecionada() {
        return musicaSelecionada;
    }

    public void setMusicaSelecionada(Musica musicaSelecionada) {
        this.musicaSelecionada = musicaSelecionada;
    }

    public long getId() {
        return id;
    }

}
