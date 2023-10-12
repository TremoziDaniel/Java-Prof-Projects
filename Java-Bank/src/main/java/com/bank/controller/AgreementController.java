package com.bank.controller;

import com.bank.converter.AgreementConverter;
import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AgreementDto;
import com.bank.domain.entity.Agreement;
import com.bank.service.AgreementService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agreements")
public class AgreementController {

    private final AgreementService service;

    private final EntityConverter<Agreement, AgreementDto> converter;

    public AgreementController(AgreementService service,
                               AgreementConverter converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AgreementDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgreementDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping("/{accountId}/{productId}")
    @ResponseStatus(HttpStatus.CREATED)
    public AgreementDto create(@PathVariable("accountId") String accountId,
                                               @PathVariable("productId") long productId,
                                               @RequestBody AgreementDto agreement) {
        return converter.toDto(service.create(accountId, productId, converter.toEntity(agreement)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgreementDto update(@PathVariable("id") long id,
                               @RequestBody Agreement agreement) {
        return converter.toDto(service.update(id, agreement));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}