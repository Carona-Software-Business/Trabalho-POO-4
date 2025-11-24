package repository;

import entity.Banda;
import jakarta.enterprise.context.ApplicationScoped;
import java.util.List;

@ApplicationScoped
public class BandaRepositoryJPA extends GenericRepositoryJPA<Banda> implements BandaRepository {

    public BandaRepositoryJPA() {
        super(Banda.class);
    }

    @Override
    public List<Banda> buscarPorNomeParcial(String nome) {
        return em.createQuery("SELECT b FROM Banda b WHERE LOWER(b.nome) LIKE LOWER(:nome)", Banda.class)
                 .setParameter("nome", "%" + nome + "%")
                 .getResultList();
    }
}
