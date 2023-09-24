package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.entity.Account;
import com.bank.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
public class AccountController {

    // Ask about rest http requests, response entities
    private final AccountService service;

    private final EntityConverter<Account, AccountDto> converter;
    //private final AccountConverter converter;

    public AccountController(AccountService service,
                             EntityConverter<Account, AccountDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<AccountDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AccountDto getById(@PathVariable("id") String id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<AccountDto> create(@RequestBody Account account) {
        return ResponseEntity.ok(
                converter.toDto(service.create(account)));
    }

    @PutMapping("/{id}")
    public AccountDto update(@PathVariable("id") String id,
                             @RequestBody Account account) {
        return converter.toDto(service.update(id, account));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping("/{id}")
    public BigDecimal getBalance(@PathVariable("id") String id) {
        return service.getBalance(id);
    }

    @PatchMapping("changeStatus/{id}")
    public void changeStatus(@PathVariable("id") String id) {
        service.changeStatus(id);
    }

    @PatchMapping("changeCurrency/{id}/{currencyId}")
    public AccountDto changeCurrency(@PathVariable("id") String id,
                                     @PathVariable("currencyId") long currencyId) {
        return converter.toDto(service.changeCurrency(id, currencyId));
    }

    @PatchMapping("topup/{id}/{amount}")
    public AccountDto topUp (@PathVariable("id") String id,
                             @PathVariable("amount") BigDecimal amount) {
        return converter.toDto(service.topUp(id, amount));
    }
}