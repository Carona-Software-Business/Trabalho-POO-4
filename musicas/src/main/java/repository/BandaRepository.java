package repository;

import entity.Banda;
import java.util.List;

public interface BandaRepository {
    List<Banda> listar();
    List<Banda> buscarPorNomeParcial(String nome);
    void salvar(Banda banda);
    void remover(Banda banda);
}
