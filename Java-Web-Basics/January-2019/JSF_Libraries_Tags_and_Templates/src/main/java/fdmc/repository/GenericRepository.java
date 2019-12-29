package fdmc.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    public void save(E entity);

    public List<E> getAll();

    public E getById(K id);
}
