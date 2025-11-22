package repository;

import entity.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class UsuarioRepositoryJPA 
        extends GenericRepositoryJPA<Usuario> 
        implements UsuarioRepository {

    public UsuarioRepositoryJPA() {
        super(Usuario.class);
    }

    @Override
    public Usuario buscar(String login, String senha) {
        try {
            return em.createQuery("SELECT u FROM Usuario u WHERE u.login = :login AND u.senha = :senha", 
                Usuario.class)
                .setParameter("login", login)
                .setParameter("senha", senha)
                .getSingleResult();
        } catch (Exception ex) {
            System.out.println("--------------\n"
                    + "Não existe o usuario buscado"
                    + "\n-----------------");
            System.out.println(ex.getMessage());
            
            return null;
        }
        
    }
}
