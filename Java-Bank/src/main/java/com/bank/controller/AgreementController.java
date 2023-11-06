package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AgreementDto;
import com.bank.domain.entity.Agreement;
import com.bank.service.AgreementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agreements")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService service;

    private final EntityConverter<Agreement, AgreementDto> converter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<AgreementDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public AgreementDto getById(@PathVariable("id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody AgreementDto agreement) {
        return service.create(
                agreement.getAccountIban(), agreement.getProductId(), agreement.getCurrency(),
                converter.toEntity(agreement)).getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable("id") Long id,
                       @RequestBody Agreement agreement) {
        return service.update(id, agreement).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
