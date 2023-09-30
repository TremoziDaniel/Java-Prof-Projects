package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Currency;
import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.TransactionType;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.domain.exception.NotEnoughFundsException;
import com.bank.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    @Autowired
    private AccountService accountService;

    @Autowired
    private CurrencyService currencyService;

    public TransactionServiceImpl(TransactionRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Transaction> getAll() {
        return repository.findAll();
    }

    @Override
    public Transaction getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Transaction"));
    }

    @Override
    public Transaction create(Transaction transaction) {
        return repository.save(transaction);
    }

    @Override
    public Transaction update(long id, Transaction transaction) {
        transaction.setId(id);

        return repository.save(transaction);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public Transaction transfer(String creditAccId, String debitAccId, BigDecimal amount) {
        Account creditAcc = accountService.getById(creditAccId);
        Account debitAcc = accountService.getById(debitAccId);
        Currency currency = creditAcc.getCurrency();

        if (!creditAcc.isStatus()) {
            throw new EntityNotAvailableException(String.format("credit account %s isn't active", creditAccId));
        } else if (!debitAcc.isStatus()) {
            throw new EntityNotAvailableException(String.format("debit account %s isn't active", debitAccId));
        }

        if (amount.compareTo(creditAcc.getBalance()) < 0) {
            throw new NotEnoughFundsException(String.format(
                    "Account %s have less than %.2f %s", creditAccId, amount, currency.getCurrencyAbb()));
        }
        // Does this save changes(account update)?
        creditAcc.setBalance(creditAcc.getBalance().subtract(amount));
        debitAcc.setBalance(debitAcc.getBalance().add(currencyService.convertCurrency(
                creditAcc.getCurrency().getId(), debitAcc.getCurrency().getId(), debitAcc.getBalance()))
                .setScale(2, RoundingMode.HALF_EVEN));
        // Why do I need transaction field?
        return create(new Transaction(creditAcc, debitAcc, TransactionType.PERSONAL, currency,
                amount, "successful", LocalDateTime.now()));
    }
}