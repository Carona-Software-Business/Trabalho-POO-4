package repository;

import entity.Musica;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class MusicaRepositoryJPA extends GenericRepositoryJPA<Musica> implements MusicaRepository {

    public MusicaRepositoryJPA() {
        super(Musica.class);
    }

    @Override
    public Musica buscarPorNome(String nome) {
        return em.createQuery("SELECT m FROM Musica m WHERE m.nome = :nome", Musica.class)
                .setParameter("nome", nome).getSingleResult();
    }

    @Override
    public List<Musica> buscarPorNomeParcial(String nome) {
        return em.createQuery("SELECT m FROM Musica m WHERE LOWER(m.nome) LIKE LOWER(:nome)", Musica.class)
                .setParameter("nome", "%" + nome + "%").getResultList();
    }

    @Override
    public List<Musica> buscarPorNomeGenero(String nomeGenero) {
        return em.createQuery("SELECT m FROM Musica m WHERE LOWER(m.genero.nome) LIKE LOWER(:genero)", Musica.class)
                .setParameter("genero", "%" + nomeGenero + "%").getResultList();
    }

    @Override
    public List<Musica> buscarPorNomeBanda(String nomeBanda) {
        return em.createQuery("SELECT m FROM Musica m WHERE LOWER(m.banda.nome) LIKE LOWER(:banda)", Musica.class)
                .setParameter("banda", "%" + nomeBanda + "%").getResultList();
    }

}
