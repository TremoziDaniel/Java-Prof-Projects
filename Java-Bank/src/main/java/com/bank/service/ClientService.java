package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;

import java.util.List;

public interface ClientService {

    List<Client> getAll();

    Client getById(String id);

    Client create(Long managerId, Long personalDataId, Client client);

    Client update(String id, Client client);

    void delete(String id);

    Client changeStatus(String id);

    List<Account> getAccounts(String id);

    Client getByTaxCode(String taxCode);

    Client getCurrent();
}
