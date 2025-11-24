package repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.util.List;

public class GenericRepositoryJPA<T> implements GenericRepository<T> {

    @PersistenceContext
    protected EntityManager em;

    private final Class<T> classe;

    public GenericRepositoryJPA(Class<T> classe) {
        this.classe = classe;
    }

    @Override
    public T buscarPorID(long id) {
        return em.find(classe, id);
    }

    @Override
    public List<T> listar() {
        return em.createQuery(
                "SELECT e FROM " + classe.getSimpleName() + " e", classe)
                .getResultList();
    }

    @Override
    @Transactional
    public void salvar(T entidade) {
        em.merge(entidade);
    }

    @Override
    @Transactional
    public T atualizar(T entidade) {
        return em.merge(entidade);
    }

    @Override
    @Transactional
    public void remover(T entidade) {
        em.remove(em.merge(entidade));
    }

}
