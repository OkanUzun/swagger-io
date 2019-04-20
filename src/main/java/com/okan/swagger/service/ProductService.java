package com.okan.swagger.service;

import com.okan.swagger.domain.Product;

import java.util.Optional;

public interface ProductService {
    Iterable listAllProducts();

    Product getProductById(Integer id);

    void deleteProduct(Integer id);

    void saveProduct(Product product);
}
