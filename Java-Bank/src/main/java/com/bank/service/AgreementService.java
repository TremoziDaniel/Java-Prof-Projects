package com.bank.service;

import com.bank.domain.entity.Agreement;

import java.util.List;

public interface AgreementService {

    public List<Agreement> getAll();

    public Agreement getById(long id);

    public Agreement create(Agreement agreement);

    public Agreement update(long id, Agreement agreement);

    public void delete(long id);
}