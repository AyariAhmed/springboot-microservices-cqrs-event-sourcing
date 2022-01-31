package com.cqrs.query;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {
    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<ProductEntity> getProducts(){
        return this.productRepository.findAll();
    }

    public ProductEntity getProductByRef(String ref){
        return this.productRepository.findById(ref).orElse(null);
    }

    public void addProduct(ProductEntity product){
        this.productRepository.save(product);
    }

    public List<ProductEntity> purgeDb() {
        this.productRepository.deleteAll(this.productRepository.findAll());
        return this.productRepository.findAll();
    }
}
