package com.bank.converter;

import com.bank.domain.dto.ManagerDto;
import com.bank.domain.entity.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerConverter implements EntityConverter<Manager, ManagerDto>{

    @Override
    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(manager.getId(), manager.isStatus(), null,
                manager.getCreatedAt(), manager.getUpdatedAt());
    }

    @Override
    public Manager toEntity(ManagerDto managerDto) {
        return new Manager(managerDto.getId(), managerDto.isStatus(), null,
                managerDto.getCreatedAt(), managerDto.getUpdatedAt());
    }
}
