package chushki.repository;

import chushki.domain.entities.Product;

public interface ProductRepository extends GenericRepository<Product,String> {
    public Product findByName(String name);
}
