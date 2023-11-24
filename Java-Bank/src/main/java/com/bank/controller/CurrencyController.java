package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.CurrencyDto;
import com.bank.domain.entity.Currency;
import com.bank.service.CurrencyService;
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

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Currencies controller",
        description = "Currency controller for CRUD and changing rate operations.")
@RestController
@RequestMapping("currencies")
@RequiredArgsConstructor
public class CurrencyController {

    public final CurrencyService service;

    private final EntityConverter<Currency, CurrencyDto> converter;

    @Operation(summary = "Get all currencies",
            description = "Returns list of all currencies.")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<CurrencyDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get currency by id.",
            description = "Returns currency by a defined id.")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyDto getById(@PathVariable("id") @Parameter(description = "Currency id") Integer id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create currency",
            description = "Creates currency from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin')")
    public Integer create(@RequestBody(description = "Currency body") Currency currency) {
        return service.create(currency).getId();
    }

    @Operation(summary = "Update currency",
            description = "Updates currency by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public Integer update(@PathVariable("id") @Parameter(description = "Currency id") Integer id,
                          @RequestBody(description = "Currency body") Currency currency) {
        return service.update(id, currency).getId();
    }

    @Operation(summary = "Delete currency",
            description = "Deletes currency by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Currency id") Integer id) {
        service.delete(id);
    }

    @Operation(summary = "Change rate of currency",
            description = "Changes currency rate by a defined id from a request body")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeRate/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public Integer changeRate(@PathVariable("id") @Parameter(description = "Currency id") Integer id,
                              @RequestBody(description = "Rate to dollar") BigDecimal rate) {
        // TODO add dependency for real time currencies rate
        return service.changeRate(id, rate).getId();
    }

    @Operation(summary = "Get currency by abbreviation",
            description = "Returns currency by a defined abbreviation of currency name.")
    @GetMapping("/abb/{abb}")
    @ResponseStatus(HttpStatus.OK)
    public CurrencyDto getCurrencyByAbb(@PathVariable("abb")
                                        @Parameter(description = "Currency abbreviation") String abb) {
        return converter.toDto(service.getByAbb(abb.toUpperCase()));
    }
}
