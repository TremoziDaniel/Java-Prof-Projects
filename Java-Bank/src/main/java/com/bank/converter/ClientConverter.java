package com.bank.converter;

import com.bank.domain.dto.ClientDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ClientConverter implements EntityConverter<Client, ClientDto>{

    @Autowired
    private ClientService service;

    @Override
    public ClientDto toDto(Client client) {
        String managerName = new StringBuilder().append(client.getManager().getPersonalData().getFirstName())
                .append(" ").append(client.getManager().getPersonalData().getFirstName()).toString();

        return new ClientDto(client.getId(), managerName, client.isStatus(),
                client.getTaxCode(), client.getCreatedAt(), client.getUpdatedAt());
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.getId(), null, clientDto.isStatus(), clientDto.getTaxCode(),
                null, clientDto.getCreatedAt(), clientDto.getUpdatedAt());
    }
}