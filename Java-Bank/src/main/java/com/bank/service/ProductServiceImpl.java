package com.bank.service;

import com.bank.domain.entity.Product;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository repository;

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidArgumentException("Product"));
    }

    @Override
    public Product create(Product product) {
        return repository.save(product);
    }

    @Override
    public Product update(long id, Product product) {
        product.setId(id);

        return repository.save(product);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}