package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.service.ClientService;
import com.bank.service.ManagerService;
import com.bank.service.PersonalDataService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "PersonalData controller",
        description = "Personal data controller for CRUD and unveiling fields for authentication.")
@RestController
@RequestMapping("personalData")
@RequiredArgsConstructor
public class PersonalDataController {

    private final PersonalDataService service;

    private final ClientService clientService;

    private final ManagerService managerService;

    private final EntityConverter<PersonalData, PersonalDataDto> converter;

    @Operation(summary = "Get all personal data",
            description = "Returns list of all personal data.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<PersonalDataDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get personal data by id",
            description = "Returns personal data by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public PersonalDataDto getById(@PathVariable("id")
                                   @Parameter(description = "Personal data id") Long id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create personal data",
            description = "Creates personal data from a request body.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Long create(@RequestBody(description = "Personal data body") PersonalDataDto personalData) {
        // TODO make personalData id unique for both manager and client
        return service.create(converter.toEntity(personalData)).getId();
    }

    @Operation(summary = "Update personal data",
            description = "Updates personal data by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public Long update(@PathVariable("id") @Parameter(description = "Personal data id") Long id,
                       @RequestBody(description = "Personal data body") PersonalDataDto personalData) {
        return service.update(id, converter.toEntity(personalData)).getId();
    }

    @Operation(summary = "Delete personal data",
            description = "Deletes personal data by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Personal data id") Long id) {
        service.delete(id);
    }

    @Operation(summary = "Get protected data from personal data",
            description = "Returns data that is needed for authentication purposes.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}/protectedData")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'manager', 'admin')")
    public List<String> getProtectedData(@PathVariable("id")
                                         @Parameter(description = "Personal data id") Long id) {
        validatePersonalData(id);

        return service.getProtectedData(id);
    }

    @Operation(summary = "Get current personal data",
            description = "Returns personal data with which you was authorized.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'manager', 'admin')")
    public PersonalDataDto getCurrentPersonalData() {
        return converter.toDto(service.getCurrentPersonalData());
    }

    private void validatePersonalData(Long id) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(auth -> auth.getAuthority().equals("Client"))) {
            Client clientCurrent = clientService.getCurrent();

            if (!clientCurrent.getPersonalData().getId().equals(id)) {
                throw new EntityNotFoundException(String.format(
                        "Unmatched id. Your personalData id is %d.", clientCurrent.getPersonalData().getId()));
            }
        } if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().noneMatch(auth -> auth.getAuthority().equals("Manager"))) {
            Manager managerCurrent = managerService.getCurrent();

            if (!managerCurrent.getPersonalData().getId().equals(id)) {
                throw new EntityNotFoundException(String.format(
                        "Unmatched id. Your personalData id is %d.", managerCurrent.getPersonalData().getId()));
            }
        }
    }
}
