package com.bank.service;

import com.bank.domain.entity.Client;
import com.bank.domain.entity.Manager;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.domain.security.UserAuthenticationDao;
import com.bank.repository.ClientRepository;
import com.bank.repository.ManagerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(username);
        System.out.println(client);
        if (client == null) {
            Manager manager = managerRepository.findByEmail(username);

            if (UserAuthenticationDao.getAdminEmail().equals(username)) {
                return UserAuthenticationDao.admin();
            } else if (manager == null) {
                throw new EntityNotFoundException(String.format("No user with email: %s", username));
            }

            return UserAuthenticationDao.fromManager(manager);
        }

        return UserAuthenticationDao.fromClient(client);
    }
}
