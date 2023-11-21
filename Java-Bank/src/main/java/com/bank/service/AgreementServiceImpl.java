package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Agreement;
import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Product;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.AccountRepository;
import com.bank.repository.AgreementRepository;
import com.bank.repository.CurrencyRepository;
import com.bank.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository repository;

    private final AccountRepository accountRepository;

    private final ProductRepository productRepository;

    private final CurrencyRepository currencyRepository;

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
        Account account = accountRepository.findByIban(accountIban).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account with iban %s.", accountIban)));
        Product product = productRepository.findById(productId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Product %d.", productId)));
        Currency currency = currencyRepository.findByCurrencyAbb(currencyAbb).orElseThrow(() ->
                new EntityNotFoundException(String.format("Currency with abb %s.", currencyAbb)));
        validateStatus(account, product);
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
        agreement.setCreatedAt(oldAgreement.getCreatedAt());
        agreement.setUpdatedAt(LocalDateTime.now());

        return repository.save(agreement);
    }

    @Override
    public void delete(Long id) {
        Agreement agreement = getById(id);
        repository.delete(agreement);
    }

    @Override
    public Agreement changeStatus(Long id) {
        Agreement agreement = getById(id);
        agreement.setStatus(!agreement.isStatus());
        agreement.setUpdatedAt(LocalDateTime.now());

        return repository.save(agreement);
    }

    private void validateStatus(Account account, Product product) {
        if (!account.isStatus()) {
            throw new EntityNotAvailableException(String.format("Account %s is not available.", account.getIban()));
        } if (!product.isStatus()) {
            throw new EntityNotAvailableException(String.format("Product %d is not available.", product.getId()));
        }
    }
}
