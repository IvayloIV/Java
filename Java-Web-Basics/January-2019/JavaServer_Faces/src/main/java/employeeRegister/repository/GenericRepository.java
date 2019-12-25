package employeeRegister.repository;

import java.util.List;

public interface GenericRepository<E, K> {
    public E save(E entity);

    public List<E> getAll();

    public E findById(K id);
}
