package beans;

import entity.Banda;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.io.Serializable;
import java.util.List;
import repository.BandaRepository;

@Named
@ViewScoped
public class BandasBean implements Serializable {

    @Inject
    private BandaRepository bandaRepository;

    private List<Banda> bandas;
    
    private String nomeBanda;

    private String nomeBusca;

    private String mensagem;

    @Inject
    private UsuarioBean usuarioBean;

    private Banda bandaSelecionada;

    @PostConstruct
    public void init() {
        listar();

 
        String idParam = FacesContext.getCurrentInstance()
            .getExternalContext()
            .getRequestParameterMap()
            .get("idBanda");

        if (idParam != null) {
            try {
                Long id = Long.parseLong(idParam);
                bandaSelecionada = bandaRepository.buscarPorID(id);
            } catch (NumberFormatException e) {
                mensagem = "ID inválido para edição.";
            }
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
        return "editarBanda?faces-redirect=true&idBanda=" + banda.getId();
    }
    
    public void salvarEdicao() {
        try {
            bandaRepository.salvar(bandaSelecionada);
            mensagem = "Nome da banda atualizado com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao atualizar banda!";
        }
    }
    
    public String cadastrar() {
        Banda novaBanda = new Banda(nomeBanda);
        
        try {
            bandaRepository.salvar(novaBanda);
            mensagem = "Banda cadastrada com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro ao cadastrar banda!";
        } finally {
            return "";
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
    
    public void listar() {
        bandas = bandaRepository.listar();
        if (bandas.isEmpty()) {
            mensagem = "Nenhuma banda encontrada!";
        }
    }

    public String getNomeBanda() {
        return nomeBanda;
    }
    
    
}
