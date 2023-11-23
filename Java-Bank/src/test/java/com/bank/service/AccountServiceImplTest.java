package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.AccountType;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.AccountRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class AccountServiceImplTest {
    // @Disabled
    // TODO Tests for controllers
    @InjectMocks
    private AccountServiceImpl service;

    @Mock
    private AccountRepository repository;

    private static final Account account1;

    private static final Account account2;

    private static final Transaction transaction;

    static {
        account1 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                "BI-12-A1B2-1234567", null, "Account1", AccountType.AUTOMATIC,
                true, null, null, null);
        account2 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                "IB-12-A1B2-1234567", null, "Account2", AccountType.AUTOMATIC,
                true, null, null, null);
        transaction = new Transaction(account1, account2,
                null, null, null, null, null);
        account1.setTransactionsCredit(List.of(transaction));
        account2.setTransactionsDebit(List.of(transaction));
    }

    // All services have this same method, so there is only one test of it here.
    @Test
    void getAll() {
        Mockito.when(repository.findAll())
                .thenReturn(Arrays.asList(account1, account2));

        assertEquals(service.getAll().toString(), Arrays.asList(account1, account2).toString());

        Mockito.when(repository.findAll()).thenReturn(List.of());

        assertThrows(EntityNotFoundException.class, () -> service.getAll());
    }

    @Test
    void getTransactions() {
        Mockito.when(repository.findByIban("BI-12-A1B2-1234567")).thenReturn(Optional.of(account1));

        assertEquals(service.getTransactions("BI-12-A1B2-1234567"), List.of(transaction));
    }

    @Test
    void topUp() {
        Mockito.when(repository.findByIban("BI-12-A1B2-1234567")).thenReturn(Optional.of(account1));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(ans -> ans.getArguments()[0]);
        BigDecimal amount = new BigDecimal("300.212");
        Account testAccount = account1;
        testAccount.setBalance(testAccount.getBalance().add(new BigDecimal("300.21")));

        assertEquals(service.topUp("BI-12-A1B2-1234567", amount), testAccount);
    }
}
