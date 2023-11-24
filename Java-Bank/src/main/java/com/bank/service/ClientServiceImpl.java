package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.ClientRepository;
import com.bank.repository.ManagerRepository;
import com.bank.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final ManagerRepository managerRepository;

    private final PersonalDataRepository personalDataRepository;

    @Override
    public List<Client> getAll() {
        List<Client> clients = repository.findAll();
        if (clients.isEmpty()) {
            throw new EntityNotFoundException("Clients.");
        }

        return clients;
    }

    @Override
    public Client getById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Client %s.", id)));
    }

    @Override
    public Client create(Long managerId, Long personalDataId, @Valid Client client) {
        Manager manager = managerRepository.findById(managerId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Manager %d.", managerId)));
        PersonalData personalData = personalDataRepository.findById(personalDataId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Personal Data %d.", personalDataId)));
        validateManagerStatus(manager);
        client.setManager(manager);
        client.setPersonalData(personalData);
        client.setCreatedAt(LocalDateTime.now());

        return repository.save(client);
    }

    @Override
    public Client update(String id, @Valid Client client, Long managerId) {
        Client oldClient = getById(id);
        Manager manager = managerRepository.findById(managerId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Manager %d.", managerId)));
        validateManagerStatus(manager);
        client.setId(UUID.fromString(id));
        client.setManager(manager);
        client.setStatus(oldClient.isStatus());
        client.setPersonalData(oldClient.getPersonalData());
        client.setCreatedAt(oldClient.getCreatedAt());
        client.setUpdatedAt(LocalDateTime.now());

        return repository.save(client);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    @Transactional
    public Client changeStatus(String id) {
        Client client = getById(id);
        client.setAccounts(client.getAccounts().stream().peek(acc ->
                acc.setStatus(!client.isStatus())).collect(Collectors.toList()));
        client.setStatus(!client.isStatus());
        client.setUpdatedAt(LocalDateTime.now());

        return repository.save(client);
    }

    @Override
    public List<Account> getAccounts(String id) {
        return getById(id).getAccounts();
    }

    @Override
    public Client getByTaxCode(String taxCode) {
        return repository.findByTaxCode(taxCode).orElseThrow(() ->
                new EntityNotFoundException(String.format("Client with tax code %s.", taxCode)));
    }

    @Override
    public Client getCurrent() {
        Client client = repository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (client == null) {
            throw new EntityNotFoundException("No assigned client.");
        }

        return client;
    }
    private void validateManagerStatus(Manager manager) {
        if (!manager.isStatus()) {
            throw new EntityNotAvailableException(String.format("Manager %s isn't active.", manager.getId()));
        }
    }
}
