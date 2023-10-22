package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.CannotBeCreatedException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.ClientRepository;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    @Autowired
    private ManagerService managerService;

    @Autowired
    private PersonalDataService personalDataService;

    public ClientServiceImpl(ClientRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Client> getAll() {
        List<Client> clients = repository.findAll();
        if (clients.isEmpty()) {
            throw new ItemNotFoundException("Clients");
        }

        return clients;
    }

    @Override
    public Client getById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(() ->
                new ItemNotFoundException(String.format("Client %s", id)));
    }

    @Override
    public Client create(long managerId, long personalDataId, Client client) {
        try {
            client.setManager(managerService.getById(managerId));
            client.setPersonalData(personalDataService.getById(personalDataId));
        } catch (ItemNotFoundException e) {
            throw new CannotBeCreatedException(String.format("Client %s", client.getId()), e);
        }

        return repository.save(client);
    }

    @Override
    public Client update(String id, Client client) {
        Client oldClient = getById(id);
        client.setId(UUID.fromString(id));
        client.setManager(oldClient.getManager());
        client.setPersonalData(oldClient.getPersonalData());

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
        client.getAccounts().stream().peek(acc -> acc.setStatus(!client.isStatus())).collect(Collectors.toList());
        client.setStatus(!client.isStatus());
        repository.save(client);
    }

    @Override
    public PersonalData getPersonalData(String id) {
        return getById(id).getPersonalData();
    }

    @Override
    public Client getClientByTaxCode(String taxCode) {
        return getAll().stream().filter(c -> c.getTaxCode().equals(taxCode)).findFirst()
                .orElseThrow(() -> new ItemNotFoundException(String.format("Client with tax code %s", taxCode)));
    }
}
