package beans;

import entity.Usuario;
import jakarta.annotation.PostConstruct;
import jakarta.ejb.Startup;
import jakarta.inject.Inject;
import jakarta.ejb.Singleton;
import repository.UsuarioRepository;

@Singleton
@Startup
public class AdminInicializador {

    @Inject
    private UsuarioRepository usuarioRepository;

    @PostConstruct
    public void init() {
        try {
            Usuario usuarioBanco = usuarioRepository.buscar("admin", "admin");

            if (usuarioBanco == null) {
                Usuario novoAdmin = new Usuario("admin", "admin", "admin", true);

                usuarioRepository.salvar(novoAdmin);
                
            }

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
