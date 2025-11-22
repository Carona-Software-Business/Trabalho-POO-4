package repository;

import entity.Usuario;

public interface UsuarioRepository extends GenericRepository<Usuario> {
    public Usuario buscar(String login, String senha);
}
