package metube.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    public void save(E entity);

    public List<E> findAll();

    public E findById(K id);
}
