package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.PersonalData;
import com.bank.service.PersonalDataService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("personalData")
@RequiredArgsConstructor
public class PersonalDataController {

    private final PersonalDataService service;

    private final EntityConverter<PersonalData, PersonalDataDto> converter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<PersonalDataDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public PersonalDataDto getById(@PathVariable("id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody PersonalDataDto personalData) {
        return service.create(converter.toEntity(personalData)).getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable("id") Long id,
                                  @RequestBody PersonalDataDto personalData) {
        return service.update(id, converter.toEntity(personalData)).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }

    @GetMapping("/{id}/protectedData")
    @ResponseStatus(HttpStatus.OK)
    public List<String> getProtectedData(@PathVariable("id") Long id) {
        PersonalData personalData = service.getById(id);

        return Arrays.asList(personalData.getPhoneNumber(), personalData.getEmail(), personalData.getPassword());
    }
}
