package com.bank.service;

import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Manager;
import com.bank.domain.entity.Product;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.CurrencyRepository;
import com.bank.repository.ManagerRepository;
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

    private final ManagerRepository managerRepository;

    private final CurrencyRepository currencyRepository;

    @Override
    public List<Product> getAll() {
        List<Product> products = repository.findAll();
        if (products.isEmpty()) {
            throw new EntityNotFoundException("Products.");
        }

        return products;
    }

    @Override
    public Product getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Product %d.", id)));
    }

    @Override
    public Product create(Long managerId, String currencyAbb, @Valid Product product) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Manager %d.", managerId)));
        Currency currency = currencyRepository.findByCurrencyAbb(currencyAbb).orElseThrow(() ->
                new EntityNotFoundException(String.format("Currency with abb %s.", currencyAbb)));
        validateManagerStatus(manager);
        product.setManager(manager);
        product.setCurrency(currency);
        product.setCreatedAt(LocalDateTime.now());

        return repository.save(product);
    }

    @Override
    public Product update(Long id, @Valid Product product) {
        Product oldProduct = getById(id);
        product.setId(id);
        product.setManager(oldProduct.getManager());
        product.setCreatedAt(oldProduct.getCreatedAt());
        product.setUpdatedAt(LocalDateTime.now());

        return repository.save(product);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Product changeStatus(Long id) {
        Product product = getById(id);
        product.setStatus(!product.isStatus());
        product.setUpdatedAt(LocalDateTime.now());

        return repository.save(product);
    }

    private void validateManagerStatus(Manager manager) {
        if (!manager.isStatus()) {
            throw new EntityNotAvailableException(String.format("Manager %s isn't active.", manager.getId()));
        }
    }
}
