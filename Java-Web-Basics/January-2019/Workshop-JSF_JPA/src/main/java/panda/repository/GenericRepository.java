package panda.repository;

import java.util.List;

public interface GenericRepository<E, K> {

    public boolean save(E entity);

    public E getById(K id);

    public List<E> getAll();

    public Long count();
}
