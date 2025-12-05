package repository;

import entity.Musica;
import java.util.List;

public interface MusicaRepository extends GenericRepository<Musica> {

    public Musica buscarPorNome(String nome);

    public List<Musica> buscarPorNomeParcial(String nome);

    public List<Musica> buscarPorNomeGenero(String nomeGenero);
    
    public List<Musica> buscarPorNomeBanda(String nomeBanda);
    
    public boolean musicaTemFavoritos(Long musicaID);
}
