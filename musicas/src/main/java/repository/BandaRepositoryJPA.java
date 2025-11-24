package repository;

import entity.Banda;
import jakarta.enterprise.context.Dependent;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.io.Serializable;
import java.util.List;

@Dependent
public class BandaRepositoryJPA implements BandaRepository, Serializable{
    private static final long serialVersionUID = 1L;
    
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Banda> listar() {
        return em.createQuery("SELECT b FROM Banda b", Banda.class).getResultList();
    }

    @Override
    public List<Banda> buscarPorNomeParcial(String nome) {
        return em.createQuery("SELECT b FROM Banda b WHERE LOWER(b.nome) LIKE LOWER(:nome)", Banda.class)
                 .setParameter("nome", "%" + nome + "%")
                 .getResultList();
    }

    @Override
    @Transactional
    public void salvar(Banda banda) {
        if (banda.getId() == 0) {
            em.persist(banda);
        } else {
            em.merge(banda);
        }
    }

    @Override
    @Transactional
    public void remover(Banda banda) {
        Banda b = em.find(Banda.class, banda.getId());
        if (b != null) {
            em.remove(b);
        }
    }
}
