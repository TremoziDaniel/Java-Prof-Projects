package com.bank.service;

import com.bank.domain.entity.Agreement;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.AgreementRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class AgreementServiceImpl implements AgreementService {

    @Autowired
    private AgreementRepository repository;

    @Override
    public List<Agreement> getAll() {
        return repository.findAll();
    }

    @Override
    public Agreement getById(long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidArgumentException("Agreement"));
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