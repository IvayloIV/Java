package chushki.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    public void save(E entity);

    public E findById(K id);

    public List<E> findAll();
}
