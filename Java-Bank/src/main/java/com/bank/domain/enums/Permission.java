package com.bank.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum Permission {

    PERMISSION_ADMIN("admin"),
    PERMISSION_MANAGER("manager"),
    PERMISSION_CLIENT("client");

    private final String permission;
}
