package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import com.bank.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService service;

    private final EntityConverter<Product, ProductDto> converter;

    public ProductController(ProductService service,
                             EntityConverter<Product, ProductDto> converter) {
        this.service = service;
        this.converter = converter;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<ProductDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping("/{managerId}")
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDto create(@PathVariable("managerId") long managerId,
                             @RequestBody ProductDto product) {
        return converter.toDto(service.create(managerId, converter.toEntity(product)));
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<ProductDto> update(@PathVariable("id") long id,
                                             @RequestBody Product product) {
        return ResponseEntity.ok(converter.toDto(service.update(id, product)));
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}