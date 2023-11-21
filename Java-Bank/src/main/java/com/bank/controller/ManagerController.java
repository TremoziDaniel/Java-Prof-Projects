package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.ManagerDto;
import com.bank.domain.entity.Manager;
import com.bank.service.ManagerService;
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

@Tag(name = "Managers controller",
        description = "Manager controller for CRUD, changing status and getting current entity operations.")
@RestController
@RequestMapping("managers")
@RequiredArgsConstructor
public class ManagerController {

    private final ManagerService service;

    private final EntityConverter<Manager, ManagerDto> converter;

    @Operation(summary = "Get all managers",
            description = "Returns list of all managers.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<ManagerDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get manager by id",
            description = "Returns manager by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public ManagerDto getById(@PathVariable("id") @Parameter(description = "Manager id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create manager",
            description = "Creates manager from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @PreAuthorize("hasAuthority('admin')")
    public Long create(@RequestBody(description = "Manager body") ManagerDto manager) {
        return service.create(manager.getPersonalDataId(), converter.toEntity(manager)).getId();
    }

    @Operation(summary = "Update manager",
            description = "Updates manager by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public Long update(@PathVariable("id") @Parameter(description = "Manager id") Long id,
                       @RequestBody(description = "Manager body") ManagerDto manager) {
        return service.update(id, converter.toEntity(manager)).getId();
    }

    @Operation(summary = "Delete manager",
            description = "Deletes manager by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Manager id") Long id) {
        service.delete(id);
    }

    @Operation(summary = "Change status of manager",
            description = "Changes status to opposite in manager by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public Long changeStatus(@PathVariable("id") @Parameter(description = "Manager id") Long id) {
        return service.changeStatus(id).getId();
    }

    @Operation(summary = "Get current manager",
            description = "Returns manager with which you was authorized.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public ManagerDto getCurrent() {
        return converter.toDto(service.getCurrent());
    }
}
