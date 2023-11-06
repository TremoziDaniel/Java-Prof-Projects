package com.bank.converter;

import com.bank.domain.dto.ClientDto;
import com.bank.domain.entity.Client;
import com.bank.service.ClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class ClientConverter implements EntityConverter<Client, ClientDto>{

    private final ClientService service;

    @Override
    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), client.getManager().getId(), client.isStatus(),
                client.getTaxCode(), client.getCreatedAt(), client.getUpdatedAt());
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.getId(), null, clientDto.isStatus(), clientDto.getTaxCode(),
                null, clientDto.getCreatedAt(), clientDto.getUpdatedAt());
    }
}
