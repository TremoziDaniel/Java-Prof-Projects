package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.AccountType;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.AccountRepository;

import com.bank.repository.ClientRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {

    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

//    @Mock
//    private ClientRepository clientRepository;

    private static Account account1;

    private static Account account2;

    private static Transaction transaction;

    static {
        account1 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                "BI-12-A1B2-1234567", null, "Account1", AccountType.AUTOMATIC,
                true, null, null, null);
        account2 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                "IB-12-A1B2-1234567", null, "Account2", AccountType.AUTOMATIC,
                true, null, null, null);
        transaction = new Transaction(account1, account2,
                null, null, null, null, null);
    }

    @BeforeEach
    void setUp() {
        Mockito.when(repository.findAll())
                .thenReturn(Arrays.asList(account1, account2));
//        Mockito.when(repository.findById(UUID.fromString("00000000-0000-0000-0000-000000000000")))
//                .thenReturn(Optional.of(account1));
//        Mockito.when(repository.findByIban("BI-12-A1B2-1234567"))
//                .thenReturn(Optional.of(account1));
//        Mockito.when(service.getByIban("BI-12-A1B2-1234567"))
//                .thenReturn(account1);
//        Mockito.when(clientRepository.findByEmail(Mockito.anyString()))
//                .thenReturn(new Client());
    }

    // All services have this same method, so there is only one test of it here.
    @Test
    void getAll() {
        Account account1 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                "BI-12-A1B2-1234567", null, "Account1", AccountType.AUTOMATIC,
                true, null, null, null);
        Account account2 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                "IB-12-A1B2-1234567", null, "Account2", AccountType.AUTOMATIC,
                true, null, null, null);

        assertEquals(service.getAll().toString(), Arrays.asList(account1, account2).toString());

        Mockito.when(repository.findAll()).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> service.getAll());
    }

    @Test
    void getTransactions() {
        assertEquals(List.of(transaction), service.getTransactions("BI-12-A1B2-1234567"));
    }

//    @Test
//    void changeStatus() {
//    }
//
//    @Test
//    void changeCurrency() {
//    }
//
//    @Test
//    void topUp() {
//    }
//
//    @Test
//    void getByIban() {
//    }
}
