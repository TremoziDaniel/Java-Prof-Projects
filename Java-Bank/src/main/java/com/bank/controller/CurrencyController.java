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
    public Currency getById(@PathVariable("id") int id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Currency create(@RequestBody Currency currency) {
        return service.create(currency);
    }

    @PatchMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Currency update(@PathVariable("id") int id,
                              @RequestBody Currency currency) {
        return service.update(id, currency);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") int id) {
        service.delete(id);
    }

    @PatchMapping("/changeCurrency/{id}/{rate}")
    @ResponseStatus(HttpStatus.OK)
    public Currency changeRate(@PathVariable("id") int id, @PathVariable("rate") BigDecimal rate) {
        return service.changeRate(id, rate);
    }

    @GetMapping("/getByAbb/{abb}")
    @ResponseStatus(HttpStatus.OK)
    public Currency getCurrencyByAbb(@PathVariable("abb") String abb) {
        return service.getCurrencyByAbb(abb);
    }
}