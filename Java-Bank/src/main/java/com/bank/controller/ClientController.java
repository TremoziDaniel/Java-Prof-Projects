package com.bank.controller;

import com.bank.converter.EntityConverter;
import com.bank.domain.dto.AccountDto;
import com.bank.domain.dto.ClientDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.service.ClientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

@Tag(name = "Clients controller",
        description = "Client controller for CRUD, management and getting current entity operations.")
@RestController
@RequestMapping("clients")
@RequiredArgsConstructor
public class ClientController {

    private final ClientService service;

    private final EntityConverter<Client, ClientDto> converter;

    private final EntityConverter<Account, AccountDto> accountConverter;

    @Operation(summary = "Get all clients",
            description = "Returns list of all clients.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public List<ClientDto> getAll() {
        return service.getAll().stream().map(
                converter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get client by id",
            description = "Returns client by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAuthority('admin')")
    public ClientDto getById(@PathVariable("id") @Parameter(description = "Client id") String id) {
        return converter.toDto(service.getById(id));
    }

    @Operation(summary = "Create client",
            description = "Creates client from a request body.")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String create(@RequestBody(description = "Client body") ClientDto client) {
        return service.create(client.getManagerId(), client.getPersonalDataId(),
                        converter.toEntity(client))
                .getId().toString();
    }

    @Operation(summary = "Update client",
            description = "Updates client by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('manager', 'admin')")
    public String update(@PathVariable("id") @Parameter(description = "Client id") String id,
                         @RequestBody(description = "Client body") ClientDto client) {
        return service.update(id, converter.toEntity(client), client.getManagerId()).getId().toString();
    }

    @Operation(summary = "Delete client",
            description = "Deletes client by a defined id from a request body.")
    @SecurityRequirement(name = "basicAuth")
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PreAuthorize("hasAuthority('admin')")
    public void delete(@PathVariable("id") @Parameter(description = "Client id") String id) {
        service.delete(id);
    }

    @Operation(summary = "Change status of client",
            description = "Changes status to in client opposite by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @PatchMapping("/changeStatus/{id}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public String changeStatus(@PathVariable("id") @Parameter(description = "Client id") String id) {
        validateClient(id);

        return service.changeStatus(id).getId().toString();
    }

    @Operation(summary = "Get all accounts from client",
            description = "Returns list of all account from client by a defined id.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/{id}/accounts")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public List<AccountDto> getAccounts(@PathVariable("id")
                                        @Parameter(description = "Client id") String id) {
        validateClient(id);

        return service.getAccounts(id).stream().map(
                accountConverter::toDto).collect(Collectors.toList());
    }

    @Operation(summary = "Get client by tax code",
            description = "Returns client by a defined tax code.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/taxCode/{taxCode}")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'manager', 'admin')")
    public ClientDto getByTaxCode(@PathVariable("taxCode")
                                  @Parameter(description = "Client tax code") String taxCode) {
        validateClientByTaxCode(taxCode);

        return converter.toDto(service.getByTaxCode(taxCode));
    }

    @Operation(summary = "Get current client",
            description = "Returns client with which you was authorized.")
    @SecurityRequirement(name = "basicAuth")
    @GetMapping("/current")
    @ResponseStatus(HttpStatus.OK)
    @PreAuthorize("hasAnyAuthority('client', 'admin')")
    public ClientDto getCurrent() {
        return converter.toDto(service.getCurrent());
    }

    private void validateClient(String id) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(auth -> auth.getAuthority().equals("Client"))) {
            Client clientCurrent = service.getCurrent();

            if (id.equals(clientCurrent.getId().toString())) {
                throw new EntityNotFoundException(String.format("Unmatched id. Your client id is %s.", clientCurrent.getId()));
            }
        }
    }

    private void validateClientByTaxCode(String taxCode) {
        if (SecurityContextHolder.getContext().getAuthentication().getAuthorities()
                .stream().anyMatch(auth -> auth.getAuthority().equals("Client"))) {
            Client clientCurrent = service.getCurrent();

            if (taxCode.equals(clientCurrent.getTaxCode())) {
                throw new EntityNotFoundException(String.format(
                        "Unmatched tax code. Your client tax code is %s.", clientCurrent.getTaxCode()));
            }
        }
    }
}
