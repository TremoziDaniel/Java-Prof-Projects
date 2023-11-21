package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.ProductDto;
import com.bank.domain.entity.Product;
import com.bank.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Products controller",
        description = "Product controller for CRUD and changing status operations.")
@RestController
@RequestMapping("products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    private final EntityConverter<Product, ProductDto> converter;

    @Operation(summary = "Get all products",
            description = "Returns list of all products.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<ProductDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get product by id",
            description = "Returns product by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public ProductDto getById(@PathVariable("id") @Parameter(description = "Product id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create product",
            description = "Creates product from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long create(@RequestBody(description = "Product body") ProductDto product) {
        return service.create(product.getManagerId(), product.getCurrency().toUpperCase(),
                converter.toEntity(product)).getId();
    }

    @Operation(summary = "Update product",
            description = "Updates product by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long update(@PathVariable("id") @Parameter(description = "Product id") Long id,
                       @RequestBody(description = "Product body") Product product) {
        return converter.toDto(service.update(id, product)).getId();
    }

    @Operation(summary = "Delete product",
            description = "Deletes product by a defined id")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('admin')")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") @Parameter(description = "Product id") Long id) {
        service.delete(id);
    }

    @Operation(summary = "Change status of product",
            description = "Changes status to opposite in product by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long changeStatus(@PathVariable("id") @Parameter(description = "Product id") Long id) {
        return service.changeStatus(id).getId();
    }
}
