package com.bank.converter;

import com.bank.domain.dto.ClientDto;
import com.bank.domain.entity.Client;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ClientConverter implements EntityConverter<Client, ClientDto>{

    @Override
    public ClientDto toDto(Client client) {
        return new ClientDto(client.getId(), new ManagerConverter().toDto(client.getManager()),
                client.isStatus(), client.getTaxCode(), client.getAccounts().stream().map(o ->
                        new AccountConverter().toDto(o)).collect(Collectors.toList()),
                client.getCreatedAt(), client.getUpdatedAt());
    }

    @Override
    public Client toEntity(ClientDto clientDto) {
        return new Client(clientDto.getId(), new ManagerConverter().toEntity(clientDto.getManager()),
                clientDto.isStatus(), clientDto.getTaxCode(), null,
                clientDto.getAccounts().stream().map(o ->
                        new AccountConverter().toEntity(o)).collect(Collectors.toList()),
                clientDto.getCreatedAt(), clientDto.getUpdatedAt()
                );
    }
}