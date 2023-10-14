package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Transaction;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
public class AccountController {
    // Ask about response entities, methods for setting other entities just by id?, managing accounts on client side?
    // @ComponentScan(@Import), @Configuration, @ModelAttribute, @Valid
    private final AccountService service;

    private final EntityConverter<Account, AccountDto> converter;
    //private final AccountConverter converter;
    @Autowired
    private EntityConverter<Transaction, TransactionDto> transactionConverter;

    public AccountController(AccountService service,
                             EntityConverter<Account, AccountDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AccountDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getById(@PathVariable("id") String id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping("/{clientId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountDto create(@PathVariable String clientId, @RequestBody AccountDto account) {
        return converter.toDto(service.create(clientId, converter.toEntity(account)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto update(@PathVariable("id") String id,
                             @RequestBody AccountDto account) {
        return converter.toDto(service.update(id, converter.toEntity(account)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping("/{id}/balance")
    @ResponseStatus(HttpStatus.OK)
    public BigDecimal getBalance(@PathVariable("id") String id) {
        return service.getBalance(id);
    }

    @GetMapping("/{id}/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getTransactions(@PathVariable("id") String id) {
        return service.getTransactions(id).stream().map(
                transactionConverter::toDto).collect(Collectors.toList());
    }

    @PatchMapping("changeStatus/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable("id") String id) {
        service.changeStatus(id);
    }

    @PatchMapping("changeCurrency/{id}/{currencyId}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto changeCurrency(@PathVariable("id") String id,
                                     @PathVariable("currencyId") int currencyId) {
        return converter.toDto(service.changeCurrency(id, currencyId));
    }

    @PatchMapping("topup/{id}/{amount}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto topUp (@PathVariable("id") String id,
                             @PathVariable("amount") BigDecimal amount) {
        return converter.toDto(service.topUp(id, amount));
    }
}