package com.bank.converter;

import com.bank.domain.dto.ManagerDto;
import com.bank.domain.entity.Manager;
import org.springframework.stereotype.Component;

@Component
public class ManagerConverter implements EntityConverter<Manager, ManagerDto>{

    @Override
    public ManagerDto toDto(Manager manager) {
        return new ManagerDto(manager.getId(), manager.isStatus(),
                manager.getCreatedAt(), manager.getUpdatedAt());
    }

    @Override
    public Manager toEntity(ManagerDto managerDto) {
        return new Manager(managerDto.getId(), null, managerDto.isStatus(),
                managerDto.getCreatedAt(), managerDto.getUpdatedAt());
    }
}
