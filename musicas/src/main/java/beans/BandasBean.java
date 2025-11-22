package beans;

import entity.Banda;
import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.BandaRepository;

@Named
@RequestScoped
public class BandasBean implements Serializable {

    @Inject
    private BandaRepository bandaRepository;

    private List<Banda> bandas;

    private String nomeBusca;

    private String mensagem;

    @Inject
    private UsuarioBean usuarioBean;

    private Banda bandaSelecionada;

    @PostConstruct
    public void listar() {
        bandas = bandaRepository.listar();
        if (bandas.isEmpty()) {
            mensagem = "Nenhuma banda encontrada!";
        }
    }

    public void buscarPorNomeParcial() {
        bandas = bandaRepository.buscarPorNomeParcial(nomeBusca);
        if (bandas.isEmpty()) {
            mensagem = "Nenhuma banda encontrada com nome " + nomeBusca + "!";
        } else {
            mensagem = "Mostrando as bandas que contém: \"" + nomeBusca + "\".";
        }
        nomeBusca = "";
    }

    public void removerBanda(Banda banda) {
        if (!usuarioBean.getUsuarioLogado().isAdministrador()) {
            mensagem = "Apenas administradores podem remover bandas.";
            return;
        }
        // Se futuramente houver relação Banda -> Musicas, pode validar aqui
        bandaRepository.remover(banda);
        listar();
        mensagem = "Banda removida com sucesso!";
    }

    public String editarBanda(Banda banda) {
        this.bandaSelecionada = banda;
        return "editarBanda?faces-redirect=true";
    }

    public void salvarEdicao() {
        try {
            bandaRepository.salvar(bandaSelecionada);
            mensagem = "Nome da banda atualizado com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao atualizar banda!";
        }
    }

    // Getters e Setters
    public List<Banda> getBandas() {
        return bandas;
    }

    public void setBandas(List<Banda> bandas) {
        this.bandas = bandas;
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

    public Banda getBandaSelecionada() {
        return bandaSelecionada;
    }
}
