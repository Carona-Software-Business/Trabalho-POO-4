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

        System.out.println(">>> [AdminInicializador] Iniciando verificação do admin...");

        // Checa se o repositório foi injetado
        if (usuarioRepository == null) {
            System.out.println(">>> [AdminInicializador] ERRO: UsuarioRepository NÃO foi injetado!");
            return;
        } else {
            System.out.println(">>> [AdminInicializador] UsuarioRepository injetado com sucesso.");
        }

        try {

            System.out.println(">>> [AdminInicializador] Buscando admin no banco...");
            Usuario usuarioBanco = usuarioRepository.buscar("admin", "admin");

            if (usuarioBanco == null) {
                System.out.println(">>> [AdminInicializador] Admin NÃO encontrado. Criando um novo...");

                Usuario novoAdmin = new Usuario("admin", "admin", "admin", true);

                usuarioRepository.salvar(novoAdmin);

                System.out.println(">>> [AdminInicializador] Admin criado com sucesso.");
            } else {
                System.out.println(">>> [AdminInicializador] Admin já existe no banco. (ID = "
                    + usuarioBanco.getId() + ")");
            }

        } catch (Exception ex) {
            System.out.println(">>> [AdminInicializador] ERRO inesperado ao verificar/criar admin:");
            ex.printStackTrace();
        }

        System.out.println(">>> [AdminInicializador] Finalizado.");
    }
}
