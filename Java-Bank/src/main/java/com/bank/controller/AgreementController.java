package com.bank.controller;

import com.bank.converter.AgreementConverter;
import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AgreementDto;
import com.bank.domain.entity.Agreement;
import com.bank.service.AgreementService;
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
    public List<AgreementDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public AgreementDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<AgreementDto> create(@RequestBody Agreement agreement) {
        return ResponseEntity.ok(
                converter.toDto(service.create(agreement)));
    }

    @PutMapping("/{id}")
    public AgreementDto update(@PathVariable("id") long id,
                               @RequestBody Agreement agreement) {
        return converter.toDto(service.update(id, agreement));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}