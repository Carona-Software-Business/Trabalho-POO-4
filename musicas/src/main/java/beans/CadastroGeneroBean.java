package beans;

import entity.Genero;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import repository.GeneroRepository;

@Named
@RequestScoped
public class CadastroGeneroBean {

    @Inject
    private GeneroRepository generoRepository;

    private Genero genero = new Genero();

    private String mensagem;

    public void cadastrarGenero() {
        try {
            generoRepository.salvar(genero);
            mensagem = "Gênero cadastrado com sucesso!";
        } catch (Exception e) {
            mensagem = "Erro, nome de gênero repetido!";
        } finally {
            genero = new Genero();
        }
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public String getMensagem() {
        return mensagem;
    }

}
