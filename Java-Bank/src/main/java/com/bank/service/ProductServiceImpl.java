package com.bank.service;

import com.bank.domain.entity.Product;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository repository;

    private final ManagerService managerService;

    @Override
    public List<Product> getAll() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("Products");
        }

        return products;
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Product %d", id)));
    }

    @Override
    public Product create(Long managerId, @Valid Product product) {
        product.setManager(managerService.getById(managerId));

        return repository.save(product);
    }

    @Override
    public Product update(Long id, @Valid Product product) {
        Product oldProduct = getById(id);
        product.setId(id);
        product.setManager(oldProduct.getManager());
        product.setUpdatedAt(LocalDateTime.now());

        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
