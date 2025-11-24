package beans;

import entity.Banda;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import repository.BandaRepository;

@Named
@ViewScoped
public class CadastroBandaBean implements Serializable {

    @Inject
    private BandaRepository bandaRepository;

    private Banda banda = new Banda();
    private String mensagem;

    public void salvar() {
        try {
            bandaRepository.salvar(banda);
            mensagem = "Banda cadastrada com sucesso!";
            banda = new Banda(); // limpa o formulário
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar banda!";
        }
    }

    public Banda getBanda() {
        return banda;
    }

    public void setBanda(Banda banda) {
        this.banda = banda;
    }

    public String getMensagem() {
        return mensagem;
    }
}
