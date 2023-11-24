package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Transaction;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.domain.exception.TooManyAccountsException;
import com.bank.service.AccountService;
import com.bank.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Accounts controller",
        description = "Account controller for CRUD and other basic management operations.")
@RestController
@RequestMapping("accounts")
@RequiredArgsConstructor
public class AccountController {
    // TODO validation
    // @ComponentScan(@Import), @Configuration, @ModelAttribute
    private final AccountService service;

    private final EntityConverter<Account, AccountDto> converter;

    private final ClientService clientService;

    private final EntityConverter<Transaction, TransactionDto> transactionConverter;

    @Operation(summary = "Get all accounts",
            description = "Returns list of all accounts.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<AccountDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get account by id",
            description = "Returns account by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public AccountDto getById(@PathVariable("id") @Parameter(description = "Account id") String id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create account",
            description = "Creates account from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('client', 'manager', 'admin')")
    public String create(@RequestBody(description = "Account body") AccountDto account) {
        validateAccountCreationClient(account.getClientId());

        return service.create(
                account.getClientId(), account.getCurrencyAbb().toUpperCase(), converter.toEntity(account)).getIban();
    }

    @Operation(summary = "Update account",
            description = "Updates existing account by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public String update(@PathVariable("id") @Parameter(description = "Account id") String id,
                         @RequestBody(description = "Account body") AccountDto account) {
        return service.update(id, converter.toEntity(account)).getIban();
    }

    @Operation(summary = "Delete account",
            description = "Deletes account by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Account id") String id) {
        service.delete(id);
    }

    @Operation(summary = "Get all transactions from account",
            description = "Returns list of all transaction from account by a defined iban.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{iban}/transactions")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public List<TransactionDto> getTransactions(@PathVariable("iban")
                                                @Parameter(description = "Account iban") String iban) {
        validateAccountClient(iban);

        return service.getTransactions(iban).stream().map(
                transactionConverter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Change status of account",
            description = "Changes status to opposite in account by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeStatus/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public String changeStatus(@PathVariable("iban")
                               @Parameter(description = "Account iban") String iban) {
        validateAccountClient(iban);

        return service.changeStatus(iban).getIban();
    }

    @Operation(summary = "Change currency of account",
            description = "Converts money in account by a defined iban to another currency by a defined abbreviation.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeCurrency/{iban}/{currencyAbb}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public String changeCurrency(@PathVariable("iban")
                                 @Parameter(description = "Account iban") String iban,
                                 @PathVariable("currencyAbb")
                                 @Parameter(description = "Currency abbreviation") String currencyAbb) {
        validateAccountClient(iban);

        return service.changeCurrency(iban, currencyAbb.toUpperCase()).getIban();
    }

    @Operation(summary = "Top up account",
            description = "Top ups account by a defined iban.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/topUp/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public String topUp (@PathVariable("iban")
                         @Parameter(description = "Account iban") String iban,
                         @RequestBody(description = "Top up amount") BigDecimal amount) {
        return service.topUp(iban, amount).getIban();
    }

    @Operation(summary = "Get account by iban",
            description = "Returns account by a defined iban.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/iban/{iban}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'manager', 'admin')")
    public AccountDto getByIban(@PathVariable("iban")
                                @Parameter(description = "Account iban") String iban) {
        validateAccountClient(iban);

        return converter.toDto(service.getByIban(iban));
    }

    private void validateAccountCreationClient(String clientId) {
        if (SecurityContextHolder.getContext().getAuthentication()
                .getAuthorities().stream().anyMatch(auth -> auth.getAuthority().equals("Client"))) {
            Client clientCurrent = clientService.getCurrent();

            if (!clientCurrent.getId().toString().equals(clientId)) {
                throw new EntityNotFoundException(String.format(
                        "Client id %s that you put into request don't match with your client id.", clientId));
            } if (clientCurrent.getAccounts().size() > 4) {
                throw new TooManyAccountsException(
                        "You opened too many accounts(4)! If you need more accounts than ask your manager to open you a new one.");
            }
        }
    }

    private void validateAccountClient(String iban) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(auth -> auth.getAuthority().equals("Client"))) {
            Client clientCurrent = clientService.getCurrent();

            if (clientCurrent.getAccounts().stream().noneMatch(acc -> acc.getIban().equals(iban))) {
                throw new EntityNotFoundException(String.format(
                        "Account with iban %s on your client.", iban));
            }
        }
    }
}
