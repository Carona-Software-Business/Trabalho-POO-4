package repository;

import entity.Banda;
import java.util.List;

public interface BandaRepository extends GenericRepository<Banda>{
    List<Banda> buscarPorNomeParcial(String nome);
}
