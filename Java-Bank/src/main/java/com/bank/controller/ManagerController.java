package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.converter.PersonalDataConverter;
import com.bank.domain.dto.ManagerDto;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.Manager;
import com.bank.service.ManagerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    private final EntityConverter<Manager, ManagerDto> converter;

    private final PersonalDataConverter personalDataConverter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ManagerDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ManagerDto getById(@PathVariable("id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody ManagerDto manager) {
        return service.create(manager.getPersonalDataId(), converter.toEntity(manager)).getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable("id") Long id, @RequestBody ManagerDto manager) {
        return service.update(id, converter.toEntity(manager)).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long changeStatus(@PathVariable("id") Long id) {
        return service.changeStatus(id).getId();
    }

    @GetMapping("/{id}/personalData")
    @ResponseStatus(HttpStatus.OK)
    public PersonalDataDto getPersonalData(@PathVariable("id") Long id) {
        return personalDataConverter.toDto(service.getById(id).getPersonalData());
    }
}
