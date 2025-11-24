package beans;

import entity.Musica;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.MusicaRepository;

@Named
@ViewScoped
public class MusicasPorBandaBean implements Serializable {

    @Inject
    private MusicaRepository musicaRepository;

    private List<Musica> musicas;
    private String mensagem;

    @PostConstruct
public void init() {
    try {
        String bandaParam = FacesContext.getCurrentInstance()
                .getExternalContext()
                .getRequestParameterMap()
                .get("nomeBanda");

        if (bandaParam != null && !bandaParam.isEmpty()) {
            musicas = musicaRepository.buscarPorNomeBanda(bandaParam);
            if (musicas.isEmpty()) {
                mensagem = "Nenhuma música encontrada para a banda " + bandaParam;
            } else {
                mensagem = "Mostrando músicas da banda: " + bandaParam;
            }
        }
    } catch (Exception e) {
        mensagem = "Erro ao carregar músicas da banda: " + e.getMessage();
        e.printStackTrace();
    }
}


    public List<Musica> getMusicas() {
        return musicas;
    }

    public String getMensagem() {
        return mensagem;
    }
}
