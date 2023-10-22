package com.bank.service;

import com.bank.domain.entity.Agreement;
import com.bank.domain.exception.CannotBeCreatedException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private ProductService productService;

    public AgreementServiceImpl(AgreementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Agreement> getAll() {
        List<Agreement> agreements = repository.findAll();
        if (agreements.isEmpty()) {
            throw new ItemNotFoundException("Agreements");
        }

        return agreements;
    }

    @Override
    public Agreement getById(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Agreement %d", id)));
    }

    @Override
    public Agreement create(String accountId, long productId, Agreement agreement) {
        try {
            agreement.setAccount(accountService.getById(accountId));
            agreement.setProduct(productService.getById(productId));
        } catch (ItemNotFoundException e) {
            throw new CannotBeCreatedException(String.format("agreement %s ", agreement.getId()), e);
        }

        return repository.save(agreement);
    }

    @Override
    public Agreement update(long id, Agreement agreement) {
        Agreement oldAgreement = getById(id);
        agreement.setId(id);
        agreement.setAccount(oldAgreement.getAccount());
        agreement.setProduct(oldAgreement.getProduct());

        return repository.save(agreement);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}
