package com.bank.controller;

import com.bank.domain.entity.Currency;
import com.bank.service.CurrencyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("currencies")
public class CurrencyController {

    public final CurrencyService service;

    public CurrencyController(CurrencyService service) {
        this.service = service;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Currency getById(@PathVariable("id") long id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Currency create(@RequestBody Currency currency) {
        return service.create(currency);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Currency update(@PathVariable("id") long id,
                              @RequestBody Currency currency) {
        return service.update(id, currency);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PatchMapping("/changeCurrency/{id}/{rate}")
    @ResponseStatus(HttpStatus.OK)
    public Currency changeRate(@PathVariable("id") long id, @PathVariable("rate") BigDecimal rate) {
        return service.changeRate(id, rate);
    }
}