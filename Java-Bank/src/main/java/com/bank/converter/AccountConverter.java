package com.bank.converter;

import com.bank.domain.dto.AccountDto;
import com.bank.domain.entity.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountConverter implements EntityConverter<Account, AccountDto> {

    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), account.getIban(), account.getClient().getId().toString(),
                account.getName(), account.getType(), account.isStatus(), account.getCurrency().getCurrencyAbb(),
                account.getBalance(), account.getCreatedAt(), account.getUpdatedAt());
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        return new Account(accountDto.getId(), accountDto.getIban(), null,
                accountDto.getName(), accountDto.getType(), accountDto.isStatus(),
                null, accountDto.getCreatedAt(), accountDto.getUpdatedAt());
    }
}
