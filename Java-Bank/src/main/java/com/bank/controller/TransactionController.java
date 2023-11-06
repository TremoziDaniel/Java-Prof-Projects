package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Transaction;
import com.bank.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService service;

    private final EntityConverter<Transaction, TransactionDto> converter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDto getById(@PathVariable("id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PostMapping("/transfer/{creditAccIban}/{debitAccIban}")
    @ResponseStatus(HttpStatus.CREATED)
    public Long transfer(@PathVariable("creditAccIban") String creditAccIban,
                         @PathVariable("debitAccIban") String debitAccIban,
                         @RequestBody TransactionDto transaction) {
        return service.transfer(
                creditAccIban, debitAccIban, transaction.getAmount(), transaction.getDescription()).getId();
    }
}
