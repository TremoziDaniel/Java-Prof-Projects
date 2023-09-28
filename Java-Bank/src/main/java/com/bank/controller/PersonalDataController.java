package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.PersonalData;
import com.bank.service.PersonalDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("personalData")
public class PersonalDataController {

    private final PersonalDataService service;

    private final EntityConverter<PersonalData, PersonalDataDto> converter;

    public PersonalDataController(PersonalDataService service,
                                  EntityConverter<PersonalData, PersonalDataDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    public List<PersonalDataDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public PersonalDataDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<PersonalDataDto> create(@RequestBody PersonalDataDto personalData) {
        return ResponseEntity.ok(
                converter.toDto(service.create(converter.toEntity(personalData))));
    }

    @PutMapping("/{id}")
    public PersonalDataDto update(@PathVariable("id") long id,
                                  @RequestBody PersonalDataDto personalData) {
        return converter.toDto(service.update(id, converter.toEntity(personalData)));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/phoneNumber")
    public String getPhoneNumber(@PathVariable("id") long id) {
        return service.getPhoneNumber(id);
    }

    @GetMapping("/{id}/email")
    public String getEmail(@PathVariable("id") long id) {
        return service.getEmail(id);
    }

    @GetMapping("/{id}/password")
    public String getPassword (@PathVariable("id") long id) {
        return service.getPassword(id);
    }
}