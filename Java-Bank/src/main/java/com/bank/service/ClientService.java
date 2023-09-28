package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;

import java.util.List;

public interface ClientService {

    public List<Client> getAll();

    public Client getById(String id);

    public Client create(Client client);

    public Client update(String id, Client client);

    public void delete(String id);

    public Manager getManager(String id);

    public List<Account> getAccounts(String id);

    public void changeStatus(String id);

    public PersonalData getPersonalData(String id);
}