package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import com.bank.service.ProductService;
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
    public List<ProductDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ProductDto getById(@PathVariable("id") long id) {
        return converter.toDto(service.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductDto> create(@RequestBody Product product) {
        return ResponseEntity.ok(
                converter.toDto(service.create(product)));
    }

    @PutMapping("/{id}")
    public ProductDto update(@PathVariable("id") long id, @RequestBody Product product) {
        return converter.toDto(service.update(id, product));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") long id) {
        service.delete(id);
    }
}