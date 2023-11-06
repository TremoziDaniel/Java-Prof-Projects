package com.bank.service;

import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ClientServiceImpl implements ClientService {

    private final ClientRepository repository;

    private final ManagerService managerService;

    private final PersonalDataService personalDataService;

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
        Manager manager = managerService.getById(managerId);

        if (!manager.isStatus()) {
            throw new EntityNotAvailableException(String.format("Manager %s isn't active.", managerId));
        }

        client.setManager(manager);
        client.setPersonalData(personalDataService.getById(personalDataId));
        client.setCreatedAt(LocalDateTime.now());

        return repository.save(client);
    }

    @Override
    public Client update(String id, @Valid Client client) {
        Client oldClient = getById(id);
        client.setId(UUID.fromString(id));
        client.setManager(oldClient.getManager());
        client.setPersonalData(oldClient.getPersonalData());
        client.setUpdatedAt(LocalDateTime.now());

        return repository.save(client);
    }

    @Override
    public void delete(String id) {
        repository.deleteById(UUID.fromString(id));
    }

    @Override
    public Client changeStatus(String id) {
        Client client = getById(id);
        client.getAccounts().stream().peek(acc -> acc.setStatus(!client.isStatus())).collect(Collectors.toList());
        client.setStatus(!client.isStatus());
        client.setUpdatedAt(LocalDateTime.now());

        return repository.save(client);
    }

    @Override
    public Client getByTaxCode(String taxCode) {
        return Optional.of(repository.findByTaxCode(taxCode)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Client with tax code %s.", taxCode)));
    }
}
