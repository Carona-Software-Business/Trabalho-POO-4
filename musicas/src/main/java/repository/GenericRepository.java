package repository;

import java.util.List;

public interface GenericRepository<T> {

    public T buscarPorID(long id);

    public List<T> listar();

    public void salvar(T entidade);

    public T atualizar(T entidade);

    public void remover(T entidade);

}
