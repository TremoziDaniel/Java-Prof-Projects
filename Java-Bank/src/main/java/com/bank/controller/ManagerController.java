package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.converter.PersonalDataConverter;
import com.bank.domain.dto.ManagerDto;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.Manager;
import com.bank.service.ManagerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping("/{personalDataId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ManagerDto create(@PathVariable("personalDataId") long personalDataId,
                             @RequestBody ManagerDto manager) {
        return converter.toDto(service.create(personalDataId, converter.toEntity(manager)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDto update(@PathVariable("id") long id, @RequestBody ManagerDto manager) {
        return converter.toDto(service.update(id, converter.toEntity(manager)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void changeStatus(@PathVariable("id") long id) {
        service.changeStatus(id);
    }

    @GetMapping("/{id}/personalData")
    @ResponseStatus(HttpStatus.OK)
    public PersonalDataDto getPersonalData(@PathVariable("id") long id) {
        return personalDataConverter.toDto(service.getById(id).getPersonalData());
    }
}