package repository;

import entity.Genero;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class GeneroRepositoryJPA extends GenericRepositoryJPA<Genero> implements GeneroRepository {

    public GeneroRepositoryJPA() {
        super(Genero.class);
    }

    @Override
    public Genero buscarPorNome(String nome) {
        return em.createQuery("SELECT g FROM Genero g WHERE g.nome = :nome", Genero.class)
                .setParameter("nome", nome).getSingleResult();
    }

    @Override
    public List<Genero> buscarPorNomeParcial(String nome) {
        return em.createQuery("SELECT g FROM Genero g WHERE LOWER(g.nome) LIKE LOWER(:nome)", Genero.class)
                .setParameter("nome", "%" + nome + "%").getResultList();
    }

}
