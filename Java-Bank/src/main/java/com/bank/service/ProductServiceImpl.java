package com.bank.service;

import com.bank.domain.entity.Product;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.ProductRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        return repository.findAll();
    }

    @Override
    public Product getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Product"));
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