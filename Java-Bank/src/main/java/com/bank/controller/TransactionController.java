package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Transaction;
import com.bank.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions")
public class TransactionController {

    private final TransactionService service;

    private final EntityConverter<Transaction, TransactionDto> converter;

    public TransactionController(TransactionService service,
                                 EntityConverter<Transaction, TransactionDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PostMapping("/transfer/{creditAccId}/{debitAccId}/{amount}")
    @ResponseStatus(HttpStatus.CREATED)
    public TransactionDto transfer(@PathVariable("creditAccId") String creditAccId,
                                   @PathVariable("debitAccId") String debitAccId,
                                   @PathVariable("amount")BigDecimal amount) {
        return converter.toDto(service.transfer(creditAccId, debitAccId, amount));
    }
}