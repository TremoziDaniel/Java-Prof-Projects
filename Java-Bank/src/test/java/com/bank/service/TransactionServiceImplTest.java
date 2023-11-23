package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.AccountType;
import com.bank.domain.enums.TransactionType;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.NotEnoughFundsException;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl service;

    @Mock
    private TransactionRepository repository;

    @Mock
    private AccountRepository accountRepository;

    private static final Account account1;

    private static final Account account2;

    private static final Currency currency;

    static {
        currency = new Currency(1, "Lemon", "LMN", null);
        account1 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                "BI-12-A1B2-1234567", null, "Account1", AccountType.AUTOMATIC,
                true, currency, null, null);
        account1.setBalance(new BigDecimal(1000));
        account2 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                "IB-12-A1B2-1234567", null, "Account2", AccountType.AUTOMATIC,
                true, currency, null, null);
    }

    @Test
    void transfer() {
        Mockito.when(accountRepository.findByIban("BI-12-A1B2-1234567"))
                .thenReturn(Optional.of(account1));
        Mockito.when(accountRepository.findByIban("IB-12-A1B2-1234567"))
                .thenReturn(Optional.of(account2));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(ans -> ans.getArguments()[0]);
        Transaction transaction = service.transfer(account1.getIban(), account2.getIban(),
                new BigDecimal(100), null, null);
        Transaction testTransaction = new Transaction(account1, account2, TransactionType.SIMPLE_TRANSFER,
                currency, new BigDecimal(100),
                String.format(" Transaction on %s %s from %s to %s.", 100, currency.getCurrencyAbb(),
                        account1.getName(), account2.getName()), null);
        testTransaction.setCompletedAt(transaction.getCompletedAt());

        assertEquals(transaction.toString(), testTransaction.toString());
        assertThrows(IllegalArgumentException.class, () -> service.transfer(
                account1.getIban(), account1.getIban(), new BigDecimal(100), null, null));
        assertThrows(NotEnoughFundsException.class, () -> service.transfer(
                account1.getIban(), account2.getIban(),
                new BigDecimal(1000), null, null));
        account1.setStatus(false);
        assertThrows(EntityNotAvailableException.class, () -> service.transfer(
                account1.getIban(), account2.getIban(),
                new BigDecimal(100), null, null));
    }
}
