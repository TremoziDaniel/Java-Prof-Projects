package com.bank.domain.security;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.enums.Role;
import lombok.Getter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.List;

public class UserAuthenticationDao extends UserAuthentication {

    private static final String adminEmail = "admin@admin.com";

    @Getter
    private static final String adminPassword = "$2a$10$xCHBkLiRbeICpU/ZMsHbseboIEr5spz1QliE/8I3gGLkJgCEze0Oq";

    public UserAuthenticationDao(String email, String password, List<SimpleGrantedAuthority> authorities, boolean status, boolean accountStatus) {
        super(email, password, authorities, status, accountStatus);
    }

    public static UserDetails fromClient(Client client) {
        return new User(client.getPersonalData().getEmail(),
                client.getPersonalData().getPassword(),
                client.isStatus(), client.isStatus(), client.isStatus(),
                client.getAccounts().stream().anyMatch(Account::isStatus),
                Role.CLIENT.getGrantedAuthorities());
    }

    public static UserDetails fromManager(Manager manager) {
        return new User(manager.getPersonalData().getEmail(),
                manager.getPersonalData().getPassword(),
                manager.isStatus(), manager.isStatus(), manager.isStatus(), manager.isStatus(),
                Role.MANAGER.getGrantedAuthorities());
    }

    public static UserDetails admin() {
        return new User(adminEmail, adminPassword,
                Role.MANAGER.getGrantedAuthorities());
    }
}
