package com.bank.converter;

import com.bank.domain.dto.AccountDto;
import com.bank.domain.entity.Account;
import com.bank.domain.entity.Client;
import com.bank.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements EntityConverter<Account, AccountDto> {
    // Accounts converter problem, converting from dto to entity with null?, mapstruct?, make all converters in one?
    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), new ClientConverter().toDto(account.getClient()),
                account.getName(), account.isStatus(), account.getCurrency(),
                account.getBalance(), account.getCreatedAt(), account.getUpdatedAt());
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        return new Account(accountDto.getId(), new ClientConverter().toEntity(accountDto.getClient()),
                accountDto.getName(), accountDto.isStatus(), accountDto.getCurrency(),
                accountDto.getBalance(), accountDto.getCreatedAt(), accountDto.getUpdatedAt());
    }
}