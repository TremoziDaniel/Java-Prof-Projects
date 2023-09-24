package com.bank.converter;

import com.bank.domain.dto.AccountDto;
import com.bank.domain.entity.Account;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements EntityConverter<Account, AccountDto> {

    // Ask about cycling convertion etc
    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), /*new ClientConverter().toDto(account.getClient())*/null,
                account.getName(), account.isStatus(), account.getCurrency(),
                account.getBalance(), account.getCreatedAt(), account.getUpdatedAt());
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
       return new Account(accountDto.getId(), /*new ClientConverter().toEntity(accountDto.getClient())*/null,
               accountDto.getName(), accountDto.isStatus(),
               accountDto.getCurrency(), accountDto.getBalance(), null,
               accountDto.getCreatedAt(), accountDto.getUpdatedAt());
    }
}