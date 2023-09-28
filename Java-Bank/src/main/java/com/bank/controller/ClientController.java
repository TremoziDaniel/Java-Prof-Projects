package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.converter.PersonalDataConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.dto.ClientDto;
import com.bank.domain.dto.ManagerDto;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clients")
public class ClientController {

    private final ClientService service;

    private final EntityConverter<Client, ClientDto> converter;

    @Autowired
    private EntityConverter<Manager, ManagerDto> managerConverter;

    @Autowired
    private EntityConverter<Account, AccountDto> accountConverter;

    @Autowired
    private PersonalDataConverter personalDataConverter;

    public ClientController(ClientService service, EntityConverter<Client, ClientDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<ClientDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ClientDto getById(@PathVariable("id") String id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ClientDto> create(@RequestBody ClientDto client) {
        return ResponseEntity.ok(
                converter.toDto(service.create(converter.toEntity(client))));
    }

    @PutMapping("/{id}")
    public ClientDto update(@PathVariable("id") String id, @RequestBody ClientDto client) {
        return converter.toDto(service.update(id, converter.toEntity(client)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping("/{id}/manager")
    public ManagerDto getManager(@PathVariable("id") String id) {
        return managerConverter.toDto(service.getManager(id));
    }

    @GetMapping("/{id}/account")
    public List<AccountDto> getAccounts(@PathVariable("id") String id) {
        return service.getAccounts(id).stream().map(
                accountConverter::toDto).collect(Collectors.toList());
    }

    @PatchMapping("changeStatus/{id}")
    public void changeStatus(@PathVariable("id") String id) {
        service.changeStatus(id);
    }

    @GetMapping("{id}/personalData")
    public PersonalDataDto getPersonalData(@PathVariable("id") String id) {
        return personalDataConverter.toDto(service.getById(id).getPersonalData());
    }
}