package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.enums.AccountType;
import com.bank.repository.ClientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
class ClientServiceImplTest {

    @InjectMocks
    private ClientServiceImpl service;

    @Mock
    private ClientRepository repository;

    private static final Client client;

    private static final Account account1;

    private static final Account account2;

    static {
        client = new Client(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                null, true, "ABCDEF12A1B2C3DA", null, null, null);
        account1 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                "BI-12-A1B2-1234567", client, "Account1", AccountType.AUTOMATIC,
                true, null, null, null);
        account2 = new Account(UUID.fromString("00000000-0000-0000-0000-000000000001"),
                "IB-12-A1B2-1234567", client, "Account2", AccountType.AUTOMATIC,
                true, null, null, null);
    }

    @Test
    void changeStatus() {
        Mockito.when(repository.findById(UUID.fromString("00000000-0000-0000-0000-000000000000")))
                .thenReturn(Optional.of(client));
        Mockito.when(repository.save(Mockito.any())).thenAnswer(ans -> ans.getArguments()[0]);
        Client testClient = new Client(UUID.fromString("00000000-0000-0000-0000-000000000000"),
                null, true, "ABCDEF12A1B2C3DA", null, null, null);
        List<Account> testAccounts = Arrays.asList(account1, account2);

        assertEquals(service.changeStatus("00000000-0000-0000-0000-000000000000"), client);
        assertNotEquals(service.changeStatus("00000000-0000-0000-0000-000000000000"), testClient);
        assertNotEquals(service.changeStatus("00000000-0000-0000-0000-000000000000").getAccounts(),
                testAccounts);
    }
}
