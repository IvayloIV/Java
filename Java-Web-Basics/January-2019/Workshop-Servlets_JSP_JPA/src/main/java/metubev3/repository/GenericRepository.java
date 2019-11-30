package metubev3.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    public boolean save(E entity);

    public List<E> findAll();

    public E findById(K id);
}
