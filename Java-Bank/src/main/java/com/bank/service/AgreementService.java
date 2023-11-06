package com.bank.service;

import com.bank.domain.entity.Agreement;

import java.util.List;

public interface AgreementService {

    List<Agreement> getAll();

    Agreement getById(Long id);

    Agreement create(String accountId, Long productId, Agreement agreement);

    Agreement update(Long id, Agreement agreement);

    void delete(Long id);
}
