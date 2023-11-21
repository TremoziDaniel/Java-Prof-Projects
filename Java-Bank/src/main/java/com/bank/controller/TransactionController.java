package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Transaction;
import com.bank.service.TransactionService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Transactions controller",
        description = "Transaction controller for read, delete and transfer operations.")
@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    private final EntityConverter<Transaction, TransactionDto> converter;

    @Operation(summary = "Get all transactions",
            description = "Returns list of all transactions.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<TransactionDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get transaction by id",
            description = "Returns transaction by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'manager', 'admin')")
    public TransactionDto getById(@PathVariable("id") @Parameter(description = "Transaction id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Delete transaction",
            description = "Deletes transaction by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Transaction id") long id) {
        service.delete(id);
    }

    @Operation(summary = "Make transfer",
            description = "Makes transfer from one account to another from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping("/transfer/{creditAccIban}/{debitAccIban}")
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public Long transfer(@PathVariable("creditAccIban")
                         @Parameter(description = "Credit account iban") String creditAccIban,
                         @PathVariable("debitAccIban")
                         @Parameter(description = "Debit account iban") String debitAccIban,
                         @RequestBody(description = "Transfer details body") TransactionDto transactionDto) {
        return service.transfer(creditAccIban, debitAccIban, transactionDto.getAmount(),
                transactionDto.getDescription(), transactionDto.getType()).getId();
    }
}
