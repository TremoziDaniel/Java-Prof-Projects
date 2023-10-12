package com.bank.converter;

import com.bank.domain.dto.AccountDto;
import com.bank.domain.entity.Account;
import com.bank.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AccountConverter implements EntityConverter<Account, AccountDto> {
    // Accounts converter problem, converting from dto to entity with null?, mapstruct?, make all converters in one?
    @Autowired
    CurrencyService currencyService;

    @Override
    public AccountDto toDto(Account account) {
        String clientName = new StringBuilder().append(account.getClient().getPersonalData().getFirstName())
                .append(" ").append(account.getClient().getPersonalData().getFirstName()).toString();

        return new AccountDto(account.getId(), clientName,
                account.getName(), account.isStatus(), account.getCurrency().getCurrencyAbb(),
                account.getBalance(), account.getCreatedAt(), account.getUpdatedAt());
    }

    @Override
    public Account toEntity(AccountDto accountDto) {
        return new Account(accountDto.getId(), null, accountDto.getName(),
                accountDto.isStatus(), currencyService.getCurrencyByAbb(accountDto.getCurrency()),
                accountDto.getBalance(), accountDto.getCreatedAt(), accountDto.getUpdatedAt());
    }
}