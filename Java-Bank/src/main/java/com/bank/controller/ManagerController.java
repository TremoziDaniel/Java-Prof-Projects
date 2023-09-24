package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.ManagerDto;
import com.bank.domain.entity.Manager;
import com.bank.service.ManagerService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("agreements")
public class ManagerController {

    private final ManagerService service;

    private final EntityConverter<Manager, ManagerDto> converter;

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
    public ResponseEntity<ManagerDto> create(@RequestBody Manager manager) {
        return ResponseEntity.ok(
                converter.toDto(service.create(manager)));
    }

    @PutMapping("/{id}")
    public ManagerDto update(@PathVariable("id") long id, @RequestBody Manager manager) {
        return converter.toDto(service.update(id, manager));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }

    @PatchMapping("changeStatus/{id}")
    public void changeStatus(@PathVariable("id") long id) {
        service.changeStatus(id);
    }
}