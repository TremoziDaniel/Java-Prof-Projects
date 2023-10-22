package com.bank.service;

import com.bank.domain.entity.Product;
import com.bank.domain.exception.CannotBeCreatedException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    @Autowired
    private ManagerService managerService;

    public ProductServiceImpl(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Product> getAll() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            throw new ItemNotFoundException("Products");
        }

        return products;
    }

    @Override
    public Product getById(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Product %d", id)));
    }

    @Override
    public Product create(long managerId, Product product) {
        try {
            product.setManager(managerService.getById(managerId));
        } catch (ItemNotFoundException e) {
            throw new CannotBeCreatedException(String.format("Product %s", product.getId()), e);
        }

        return repository.save(product);
    }

    @Override
    public Product update(long id, Product product) {
        Product oldProduct = getById(id);
        product.setId(id);
        product.setManager(oldProduct.getManager());

        return repository.save(product);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
