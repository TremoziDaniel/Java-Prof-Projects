package com.bank.service;

import com.bank.domain.entity.Agreement;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.AgreementRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AgreementServiceImpl implements AgreementService {

    private final AgreementRepository repository;

    public AgreementServiceImpl(AgreementRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Agreement> getAll() {
        return repository.findAll();
    }

    @Override
    public Agreement getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Agreement"));
    }

    @Override
    public Agreement create(Agreement agreement) {
        return repository.save(agreement);
    }

    @Override
    public Agreement update(long id, Agreement agreement) {
        agreement.setId(id);

        return repository.save(agreement);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }
}