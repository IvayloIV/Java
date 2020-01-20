package casebook.repository;

import java.util.List;

public interface GenericRepository<E, K> {

    public boolean save(E entity);

    public List<E> getAll();

    public E getById(K id);

    public Long size();
}
