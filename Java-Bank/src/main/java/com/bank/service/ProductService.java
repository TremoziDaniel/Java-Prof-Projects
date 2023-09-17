package com.bank.service;

import com.bank.domain.entity.Product;

import java.util.List;

public interface ProductService {

    public List<Product> getAll();

    public Product getById(long id);

    public Product create(Product product);

    public Product update(long id, Product product);

    public void delete(long id);
}