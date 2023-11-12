package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Transaction;
import com.bank.service.AccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.util.Pair;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    // @ComponentScan(@Import), @Configuration, @ModelAttribute
    private final AccountService service;

    private final EntityConverter<Account, AccountDto> converter;

    private final EntityConverter<Transaction, TransactionDto> transactionConverter;

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

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody AccountDto account) {
        return service.create(account.getClientId(), account.getCurrency(), converter.toEntity(account)).getIban();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public String update(@PathVariable("id") String id,
                         @RequestBody AccountDto account) {
        return service.update(id, converter.toEntity(account)).getIban();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") String id) {
        service.delete(id);
    }

    @GetMapping("/{iban}/balance")
    @ResponseStatus(HttpStatus.OK)
    public Pair<String, BigDecimal> getBalance(@PathVariable("iban") String iban) {
        Account account = service.getByIban(iban);

        return Pair.of(account.getCurrency().getCurrencyAbb(), account.getBalance());
    }

    @GetMapping("/{iban}/transactions")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getTransactions(@PathVariable("iban") String iban) {
        return service.getTransactions(iban).stream().map(
                transactionConverter::toDto).collect(Collectors.toList());
    }

    @PatchMapping("/changeStatus/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public String changeStatus(@PathVariable("iban") String iban) {
        return service.changeStatus(iban).getIban();
    }

    @PatchMapping("/changeCurrency/{iban}/{currencyAbb}")
    @ResponseStatus(HttpStatus.OK)
    public String changeCurrency(@PathVariable("iban") String iban,
                                 @PathVariable("currencyAbb") String currencyAbb) {
        return service.changeCurrency(iban, currencyAbb).getIban();
    }

    @PatchMapping("/topUp/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public String topUp (@PathVariable("iban") String iban,
                         @RequestBody BigDecimal amount) {
        return service.topUp(iban, amount).getIban();
    }

    @GetMapping("/iban/{iban}")
    @ResponseStatus(HttpStatus.OK)
    public AccountDto getByIban(@PathVariable("iban") String iban) {
        return converter.toDto(service.getByIban(iban));
    }
}
