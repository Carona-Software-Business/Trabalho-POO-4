package beans;

import entity.Musica;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.faces.context.FacesContext;
import java.io.Serializable;
import java.util.List;
import repository.MusicaRepository;

@Named
@ViewScoped
public class MusicasPorGeneroBean implements Serializable {

    @Inject
    private MusicaRepository musicaRepository;

    private List<Musica> musicas;
    private String generoSelecionado;
    private String mensagem;

    @PostConstruct
    public void init() {
        String generoParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("nomeGenero");

        if (generoParam != null && !generoParam.isEmpty()) {
            generoSelecionado = generoParam;
            musicas = musicaRepository.buscarPorNomeGenero(generoSelecionado);

            if (musicas.isEmpty()) {
                mensagem = "Nenhuma música encontrada para o gênero " + generoSelecionado;
            } else {
                mensagem = "Mostrando músicas do gênero: " + generoSelecionado;
            }
        } else {
            mensagem = "Nenhum gênero informado!";
        }
    }

    public List<Musica> getMusicas() {
        return musicas;
    }

    public String getGeneroSelecionado() {
        return generoSelecionado;
    }

    public String getMensagem() {
        return mensagem;
    }
}
