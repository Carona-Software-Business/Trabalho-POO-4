package beans;

import entity.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Startup;
import jakarta.inject.Singleton;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

@Singleton
@Startup
public class AdminInicializador {
    @PersistenceContext
    private EntityManager em;
    
    @PostConstruct
    public void init() {
        // Verifica se existem um adm
        Long count = em.createQuery("SELECT COUNT(u) FROM Usuario u WHERE u.login = :login", Long.class)
                       .setParameter("login", "admin")
                       .getSingleResult();
        
        if(count == 0) {
            Usuario adm = new Usuario("adm", "admin", "admin", true);
            em.persist(adm);
        }
    }
}
