package chushki.service;

import chushki.domain.entities.Product;
import chushki.domain.models.service.ProductServiceModel;
import chushki.repository.ProductRepository;
import chushki.util.ModelMapper;

import javax.inject.Inject;

public class ProductServiceImpl implements ProductService {
    private final ModelMapper modelMapper;
    private final ProductRepository productRepository;

    @Inject
    public ProductServiceImpl(ModelMapper modelMapper, ProductRepository productRepository) {
        this.modelMapper = modelMapper;
        this.productRepository = productRepository;
    }

    @Override
    public void saveProduct(ProductServiceModel productCreateServiceModel) {
        Product product = this.modelMapper.map(productCreateServiceModel, Product.class);
        this.productRepository.save(product);
    }

    @Override
    public ProductServiceModel[] getAllProducts() {
        ProductServiceModel[] products = this.productRepository.findAll().stream()
                .map(a -> this.modelMapper.map(a, ProductServiceModel.class))
                .toArray(ProductServiceModel[]::new);
        return products;
    }

    @Override
    public ProductServiceModel getById(String id) {
        return this.modelMapper.map(this.productRepository.findById(id), ProductServiceModel.class);
    }

    @Override
    public ProductServiceModel getByName(String name) {
        return this.modelMapper.map(this.productRepository.findByName(name), ProductServiceModel.class);
    }
}
