package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import com.bank.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final EntityConverter<Product, ProductDto> converter;

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getById(@PathVariable("id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestParam Long managerId,
                       @RequestBody ProductDto product) {
        return service.create(managerId, converter.toEntity(product)).getId();
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Long update(@PathVariable("id") Long id,
                       @RequestBody Product product) {
        return converter.toDto(service.update(id, product)).getId();
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) {
        service.delete(id);
    }
}
