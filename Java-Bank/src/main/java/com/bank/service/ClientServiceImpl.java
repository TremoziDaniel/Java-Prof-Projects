package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public List<Client> getAll() {
        return repository.findAll();
    }

    @Override
    public Client getById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(() -> new InvalidArgumentException("Client"));
    }

    @Override
    public Client create(Client client) {
        return repository.save(client);
    }

    @Override
    public Client update(String id, Client client) {
        client.setId(UUID.fromString(id));

        return repository.save(client);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Manager getManager(String id) {
        return getById(id).getManager();
    }

    @Override
    public List<Account> getAccounts(String id) {
        return getById(id).getAccounts();
    }

    @Override
    public void changeStatus(String id) {
        Client client = getById(id);
        client.setStatus(!client.isStatus());
        repository.save(client);
    }
}