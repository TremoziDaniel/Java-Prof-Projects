package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AgreementDto;
import com.bank.domain.entity.Agreement;
import com.bank.service.AgreementService;
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

@Tag(name = "Agreements controller",
        description = "Agreement controller for CRUD and changing status operations.")
@RestController
@RequestMapping("agreements")
@RequiredArgsConstructor
public class AgreementController {

    private final AgreementService service;

    private final EntityConverter<Agreement, AgreementDto> converter;

    @Operation(summary = "Get all agreements",
            description = "Returns list of all agreements.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<AgreementDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get agreement by id",
            description = "Returns agreement by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public AgreementDto getById(@PathVariable("id") @Parameter(description = "Agreement id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create agreement",
            description = "Creates agreement from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long create(@RequestBody(description = "Agreement body") AgreementDto agreement) {
        return service.create(
                agreement.getAccountIban(), agreement.getProductId(),
                agreement.getCurrencyAbb().toUpperCase(), converter.toEntity(agreement)).getId();
    }

    @Operation(summary = "Update agreement",
            description = "Updates agreement by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long update(@PathVariable("id") @Parameter(description = "Agreement id") Long id,
                       @RequestBody(description = "Agreement body") Agreement agreement) {
        return service.update(id, agreement).getId();
    }

    @Operation(summary = "Delete agreement",
            description = "Deletes agreement by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Agreement id") Long id) {
        service.delete(id);
    }

    @Operation(summary = "Change status of agreement",
            description = "Changes status to opposite in agreement by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long changeStatus(@PathVariable("id") @Parameter(description = "Agreement id") Long id) {
        return service.changeStatus(id).getId();
    }
}
