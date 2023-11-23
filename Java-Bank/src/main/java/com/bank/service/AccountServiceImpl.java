package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Transaction;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.AccountRepository;
import com.bank.repository.ClientRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountServiceImpl implements AccountService {
    // TODO logger
    private final AccountRepository repository;

    private final ClientRepository clientRepository;

    private final CurrencyService currencyService;

    @Override
    public List<Account> getAll() {
        List<Account> accounts = repository.findAll();

        if (accounts.isEmpty()) {
            throw new EntityNotFoundException("Accounts.");
        }

        return accounts;
    }

    @Override
    public Account getById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account %s.", id)));
    }

    @Override
    public Account create(String clientId, String currencyAbb, @Valid Account account) {
        Client client = clientRepository.findById(UUID.fromString(clientId)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Client %s.", clientId)));
        Currency currency = currencyService.getByAbb(currencyAbb);

        if (client.isStatus()) {
            throw new EntityNotAvailableException(String.format("Client %s isn't active.", client.getId()));
        }

        account.setClient(client);
        account.setCurrency(currency);
        account.setCreatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    @Override
    public Account update(String iban, @Valid Account account) {
        Account oldAccount = getById(iban);
        account.setId(oldAccount.getId());
        account.setClient(oldAccount.getClient());
        account.setBalance(oldAccount.getBalance());
        account.setCreatedAt(oldAccount.getCreatedAt());
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    @Override
    public void delete(String id) {
        Account account = getById(id);
        repository.delete(account);
    }

    @Override
    public List<Transaction> getTransactions(String iban) {
        Account account = getByIban(iban);

        return account.compactTransactions();
    }

    @Override
    public Account changeStatus(String iban) {
        Account account = getByIban(iban);
        account.setStatus(!account.isStatus());
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    @Override
    @Transactional
    public Account changeCurrency(String iban, String currencyAbb) {
        Account account = getByIban(iban);
        validateStatus(account);
        Currency currency = currencyService.getByAbb(currencyAbb);
        account.setBalance(currencyService.convertCurrency(
                account.getCurrency(), currency, account.getBalance()));
        account.setCurrency(currency);
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    @Override
    public Account topUp(String iban, BigDecimal amount) {
        Account account = getByIban(iban);
        validateStatus(account);
        account.setBalance(account.getBalance()
                .add(amount.setScale(2, RoundingMode.HALF_EVEN)));
        account.setUpdatedAt(LocalDateTime.now());

        return repository.save(account);
    }

    @Override
    public Account getByIban(String iban) {
        return repository.findByIban(iban).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account with iban %s.", iban)));
    }

    private void validateStatus(Account account) {
        if (!account.isStatus()) {
            throw new EntityNotAvailableException(String.format("Account %s is not available.", account.getIban()));
        }
    }
}
