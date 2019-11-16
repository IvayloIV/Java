package chushki.service;

import chushki.domain.models.service.ProductServiceModel;

public interface ProductService {
    public void saveProduct(ProductServiceModel productCreateServiceModel);

    public ProductServiceModel[] getAllProducts();

    public ProductServiceModel getById(String id);

    public ProductServiceModel getByName(String name);
}
