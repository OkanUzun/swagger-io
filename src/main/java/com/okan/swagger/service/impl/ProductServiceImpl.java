package com.okan.swagger.service.impl;

import com.okan.swagger.domain.Product;
import com.okan.swagger.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.okan.swagger.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Autowired
    public ProductServiceImpl(final ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Iterable listAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public Product getProductById(final Integer id) {
        return productRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteProduct(final Integer id) {
        productRepository.deleteById(id);
    }

    @Override
    public void saveProduct(final Product product) {
        productRepository.save(product);
    }
}
