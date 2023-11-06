package com.bank.service;

import com.bank.domain.entity.Product;

import java.util.List;

public interface ProductService {

    List<Product> getAll();

    Product getById(Long id);

    Product create(Long managerId, Product product);

    Product update(Long id, Product product);

    void delete(Long id);
}
