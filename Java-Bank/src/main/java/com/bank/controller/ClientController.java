package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.converter.PersonalDataConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.dto.ClientDto;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    private final EntityConverter<Client, ClientDto> converter;

    private final EntityConverter<Account, AccountDto> accountConverter;

    private final PersonalDataConverter personalDataConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ClientDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getById(@PathVariable("id") String id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody ClientDto client) {
        return service.create(client.getManagerId(), client.getPersonalDataId(), converter.toEntity(client))
                .getId().toString();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("id") String id,
                         @RequestBody ClientDto client) {
        return service.update(id, converter.toEntity(client)).getId().toString();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping("/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getAccounts(@PathVariable("id") String id) {
        return service.getById(id).getAccounts().stream().map(
                accountConverter::toDto).collect(Collectors.toList());
    }

    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String changeStatus(@PathVariable("id") String id) {
        return service.changeStatus(id).getId().toString();
    }

    @GetMapping("/{id}/personalData")
    @ResponseStatus(HttpStatus.OK)
    public PersonalDataDto getPersonalData(@PathVariable("id") String id) {
        return personalDataConverter.toDto(service.getById(id).getPersonalData());
    }

    @GetMapping("/taxCode/{taxCode}")
    @ResponseStatus(HttpStatus.OK)
    public ClientDto getByTaxCode(@PathVariable("taxCode") String taxCode) {
        return converter.toDto(service.getByTaxCode(taxCode));
    }
}
