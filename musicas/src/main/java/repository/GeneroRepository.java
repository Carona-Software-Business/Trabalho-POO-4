package repository;

import entity.Genero;
import java.util.List;

public interface GeneroRepository extends GenericRepository<Genero> {

    public Genero buscarPorNome(String nome);

    public List<Genero> buscarPorNomeParcial(String nome);
}
