package com.bank.domain.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Arrays;
import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@AllArgsConstructor
@Getter
public enum Role {

    ADMIN(new HashSet<>{Permission.PERMISSION_ADMIN, Permission.PERMISSION_MANAGER, Permission.PERMISSION_MANAGER}),
    MANAGER(new HashSet<>(Permission.PERMISSION_MANAGER)),
    CLIENT(EnumSet(Permission.PERMISSION_CLIENT));

    private final Set<Permission> permissions;

    public Set<GrantedAuthority> getGrantedAuthorities() {
        Set<GrantedAuthority> permissions = getPermissions().stream().map(permission ->
                new SimpleGrantedAuthority(permission.getPermission())).collect(Collectors.toSet());
        permissions.add(new SimpleGrantedAuthority("ROLE_"+this.name()));

        return permissions;
    }
}
