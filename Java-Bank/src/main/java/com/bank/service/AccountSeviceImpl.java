package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Transaction;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.domain.exception.TooManyAccountsException;
import com.bank.repository.AccountRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AccountSeviceImpl implements AccountService {

    private final AccountRepository repository;

    private final ClientService clientService;

    private final CurrencyService currencyService;

    @Override
    public List<Account> getAll() {
        List<Account> accounts = repository.findAll();
        if (accounts.isEmpty()) {
            throw new EntityNotFoundException("Accounts");
        }

        return accounts;
    }

    @Override
    public Account getById(String id) {
        return repository.findById(UUID.fromString(id)).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account %s", id)));
    }

    @Override
    public Account create(String clientId, @Valid Account account) {
        Client client = clientService.getById(clientId);

        if (client.getAccounts().size() > 4) {
            throw new TooManyAccountsException("You opened too many accounts(4)! If you need more accounts than ask your manager to open you new one.");
        }

        if (client.isStatus()) {
            account.setClient(client);
            account.setBalance(new BigDecimal(0));
            account.setCreatedAt(LocalDateTime.now());
        } else {
            throw new EntityNotAvailableException(String.format("Client %s isn't active.", clientId));
        }

        return repository.save(account);
    }

    @Override
    public Account update(String iban, @Valid Account account) {
        Account oldAccount = getById(iban);
        account.setId(oldAccount.getId());
        account.setClient(oldAccount.getClient());
        account.setBalance(oldAccount.getBalance());
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
    public Account changeCurrency(String iban, String currencyAbb) {
        Account account = getByIban(iban);

        if (account.isStatus()) {
            Currency currency = currencyService.getByAbb(currencyAbb);
            account.setBalance(currencyService.convertCurrency(
                    account.getCurrency(), currency, account.getBalance()));
            account.setCurrency(currency);
            account.setUpdatedAt(LocalDateTime.now());

            return repository.save(account);
        }  else {
            throw new EntityNotAvailableException(String.format("account %s is not available", iban));
        }
    }

    @Override
    public Account topUp(String iban, BigDecimal amount) {
        Account account = getByIban(iban);

        if (account.isStatus()) {
            account.setBalance(account.getBalance()
                    .add(amount.setScale(2, RoundingMode.HALF_EVEN)));
            account.setUpdatedAt(LocalDateTime.now());

            return repository.save(account);
        } else {
            throw new EntityNotAvailableException(String.format("account %s is not available", iban));
        }
    }

    @Override
    public Account getByIban(String iban) {
        return repository.findByIban(iban).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account with iban %s", iban)));
    }
}
