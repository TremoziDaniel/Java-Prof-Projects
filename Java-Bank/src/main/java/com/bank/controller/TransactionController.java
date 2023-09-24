package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.TransactionDto;
import com.bank.domain.entity.Transaction;
import com.bank.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
    public List<TransactionDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public TransactionDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<TransactionDto> create(@RequestBody Transaction transaction) {
        return ResponseEntity.ok(
                converter.toDto(service.create(transaction)));
    }

    @PutMapping("/{id}")
    public TransactionDto update(@PathVariable("id") long id,
                                 @RequestBody Transaction transaction) {
        return converter.toDto(service.update(id, transaction));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}
