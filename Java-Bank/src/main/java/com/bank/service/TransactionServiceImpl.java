package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Transaction;
import com.bank.domain.enums.TransactionType;
import com.bank.domain.exception.EntityNotAvailableException;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.domain.exception.NotEnoughFundsException;
import com.bank.repository.AccountRepository;
import com.bank.repository.TransactionRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository repository;

    private final AccountRepository accountRepository;

    private final CurrencyService currencyService;

    @Override
    public List<Transaction> getAll() {
        List<Transaction> transactions = repository.findAll();
        if (transactions.isEmpty()) {
            throw new EntityNotFoundException("Transactions.");
        }

        return transactions;
    }

    @Override
    public Transaction getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Transaction %d.", id)));
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    @Transactional
    public Transaction transfer(String creditAccIban, String debitAccIban,
                                @NonNull BigDecimal amount, String description, TransactionType type) {
        Account creditAcc = accountRepository.findByIban(creditAccIban).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account with iban %s.", creditAccIban)));
        Account debitAcc = accountRepository.findByIban(debitAccIban).orElseThrow(() ->
                new EntityNotFoundException(String.format("Account with iban %s.", debitAccIban)));
        validateTransaction(creditAcc, debitAcc, amount);
        BigDecimal transferAmount = creditAcc.getCurrency().getId().equals(debitAcc.getCurrency().getId()) ?
                amount : currencyService.convertCurrency(
                debitAcc.getCurrency(), creditAcc.getCurrency(), amount);
        creditAcc.setBalance(creditAcc.getBalance().subtract(amount)
                .setScale(2, RoundingMode.HALF_EVEN));
        creditAcc.setUpdatedAt(LocalDateTime.now());
        debitAcc.setBalance(debitAcc.getBalance().add(transferAmount)
                .setScale(2, RoundingMode.HALF_EVEN));
        debitAcc.setUpdatedAt(LocalDateTime.now());

        return repository.save(new Transaction(creditAcc, debitAcc,
                type == null ? TransactionType.SIMPLE_TRANSFER : type,
                creditAcc.getCurrency(), amount, String.format("%s Transaction on %s %s from %s to %s.",
                description == null ? "" : description, amount, creditAcc.getCurrency().getCurrencyAbb(),
                creditAcc.getName(), debitAcc.getName()),
                LocalDateTime.now()));
    }

    private void validateTransaction(Account creditAcc, Account debitAcc, BigDecimal amount) {
        if (creditAcc.equals(debitAcc)) {
            throw new IllegalArgumentException("You can't make a transaction in one account.");
        } if (!creditAcc.isStatus()) {
            throw new EntityNotAvailableException(String.format(
                    "Credit account %s isn't active.", creditAcc.getIban()));
        } if (!debitAcc.isStatus()) {
            throw new EntityNotAvailableException(String.format(
                    "Debit account %s isn't active.", debitAcc.getIban()));
        } if (amount.compareTo(creditAcc.getBalance()) > 0) {
            throw new NotEnoughFundsException(String.format(
                    "Account %s have less than %.2f %s.",
                    creditAcc.getIban(), amount, creditAcc.getCurrency().getCurrencyAbb()));
        }
    }
}
