package com.bank.converter;

import com.bank.domain.dto.AccountDto;
import com.bank.domain.entity.Account;
import com.bank.service.CurrencyService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class AccountConverter implements EntityConverter<Account, AccountDto> {

    private final CurrencyService currencyService;

    @Override
    public AccountDto toDto(Account account) {
        return new AccountDto(account.getId(), account.getIban(), account.getClient().getId().toString(),
                account.getName(), account.isStatus(), account.getCurrency().getCurrencyAbb(),
                account.getBalance(), account.getCreatedAt(), account.getUpdatedAt());
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        return new Account(accountDto.getId(), accountDto.getIban(), null, accountDto.getName(),
                accountDto.isStatus(), currencyService.getByAbb(accountDto.getCurrency()),
                accountDto.getCreatedAt(), accountDto.getUpdatedAt());
    }
}
