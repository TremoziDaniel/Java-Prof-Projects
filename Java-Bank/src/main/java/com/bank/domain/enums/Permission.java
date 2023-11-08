package com.bank.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {

    PERMISSION_ADMIN("Admin"),
    PERMISSION_MANAGER("Manager"),
    PERMISSION_CLIENT("Client");

    private final String permission;


}
