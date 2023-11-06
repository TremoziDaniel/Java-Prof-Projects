package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Agreement;
import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Product;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.AgreementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository repository;

    private final AccountService accountService;

    private final ProductService productService;

    private final CurrencyService currencyService;

    @Override
    public List<Agreement> getAll() {
        List<Agreement> agreements = repository.findAll();
        if (agreements.isEmpty()) {
            throw new EntityNotFoundException("Agreements.");
        }

        return agreements;
    }

    @Override
    public Agreement getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Agreement %d.", id)));
    }

    @Override
    public Agreement create(String accountIban, Long productId, String currencyAbb, Agreement agreement) {
        Account account = accountService.getByIban(accountIban);
        Product product = productService.getById(productId);
        Currency currency = currencyService.getByAbb(currencyAbb);

        if (!account.isStatus()) {
            throw new EntityNotAvailableException(String.format("Account %s is not available.", accountIban));
        } if (!product.isStatus()) {
            throw new EntityNotAvailableException(String.format("Product %d is not available.", productId));
        }

        agreement.setAccount(account);
        agreement.setProduct(product);
        agreement.setCurrency(currency);
        agreement.setCreatedAt(LocalDateTime.now());

        return repository.save(agreement);
    }

    @Override
    public Agreement update(Long id, Agreement agreement) {
        Agreement oldAgreement = getById(id);
        agreement.setId(id);
        agreement.setAccount(oldAgreement.getAccount());
        agreement.setProduct(oldAgreement.getProduct());
        agreement.setUpdatedAt(LocalDateTime.now());

        return repository.save(agreement);
    }

    @Override
    public void delete(Long id) {
        Agreement agreement = getById(id);
        repository.delete(agreement);
    }
}
