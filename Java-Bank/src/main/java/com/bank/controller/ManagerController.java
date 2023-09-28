package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.converter.PersonalDataConverter;
import com.bank.domain.dto.ManagerDto;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.Manager;
import com.bank.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("managers")
public class ManagerController {

    private final ManagerService service;

    private final EntityConverter<Manager, ManagerDto> converter;

    @Autowired
    private PersonalDataConverter personalDataConverter;

    public ManagerController(ManagerService service,
                             EntityConverter<Manager, ManagerDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<ManagerDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ManagerDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ManagerDto> create(@RequestBody ManagerDto manager) {
        return ResponseEntity.ok(
                converter.toDto(service.create(converter.toEntity(manager))));
    }

    @PutMapping("/{id}")
    public ManagerDto update(@PathVariable("id") long id, @RequestBody ManagerDto manager) {
        return converter.toDto(service.update(id, converter.toEntity(manager)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PatchMapping("changeStatus/{id}")
    public void changeStatus(@PathVariable("id") long id) {
        service.changeStatus(id);
    }

    @GetMapping("{id}/personalData")
    public PersonalDataDto getPersonalData(@PathVariable("id") long id) {
        return personalDataConverter.toDto(service.getById(id).getPersonalData());
    }
}