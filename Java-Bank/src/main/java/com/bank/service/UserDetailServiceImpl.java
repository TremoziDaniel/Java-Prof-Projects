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
public class UserDetailServiceImpl implements UserDetailsService {

    private final ClientRepository clientRepository;

    private final ManagerRepository managerRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Client client = clientRepository.findByEmail(email);

        if (client == null) {
            Manager manager = managerRepository.findByEmail(email);

            if (UserAuthenticationDao.getAdminPassword().equals(email)) {
                return UserAuthenticationDao.admin();
            } if (manager == null) {
                throw new EntityNotFoundException(String.format("No user with email: %s", email));
            }

            return UserAuthenticationDao.fromManager(manager);
        }

        return UserAuthenticationDao.fromClient(client);
    }
}
