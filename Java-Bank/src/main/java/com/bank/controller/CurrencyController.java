package com.bank.controller;

import com.bank.domain.entity.Currency;
import com.bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("currencies")
@RequiredArgsConstructor
public class CurrencyController {

    public final CurrencyService service;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<Currency> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Currency getById(@PathVariable("id") Integer id) {
        return service.getById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Integer create(@RequestBody Currency currency) {
        return service.create(currency).getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Integer update(@PathVariable("id") Integer id,
                          @RequestBody Currency currency) {
        return service.update(id, currency).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Integer id) {
        service.delete(id);
    }

    @PatchMapping("/changeRate/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Integer changeRate(@PathVariable("id") Integer id, @RequestParam BigDecimal rate) {
        return service.changeRate(id, rate).getId();
    }

    @GetMapping("/abb/{abb}")
    @ResponseStatus(HttpStatus.OK)
    public Currency getCurrencyByAbb(@PathVariable("abb") String abb) {
        return service.getByAbb(abb);
    }
}
