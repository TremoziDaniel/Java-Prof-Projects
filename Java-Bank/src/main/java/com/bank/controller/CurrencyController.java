package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.CurrencyDto;
import com.bank.domain.entity.Currency;
import com.bank.service.CurrencyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("currencies")
public class CurrencyController {

    public final CurrencyService service;

    public final EntityConverter<Currency, CurrencyDto> converter;

    public CurrencyController(CurrencyService service,
                              EntityConverter<Currency, CurrencyDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<CurrencyDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public CurrencyDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<CurrencyDto> create(@RequestBody Currency currency) {
        return ResponseEntity.ok(
                converter.toDto(service.create(currency)));
    }

    @PatchMapping("/{id}")
    public CurrencyDto update(@PathVariable("id") long id,
                              @RequestBody Currency currency) {
        return converter.toDto(service.update(id, currency));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}