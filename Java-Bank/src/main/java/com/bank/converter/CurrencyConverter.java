package com.bank.converter;

import com.bank.domain.dto.CurrencyDto;
import com.bank.domain.entity.Currency;
import org.springframework.stereotype.Component;

@Component
public class CurrencyConverter implements EntityConverter<Currency, CurrencyDto> {

// Do I need it?
    @Override
    public CurrencyDto toDto(Currency currency) {
        return new CurrencyDto(currency.getId(), currency.getCurrencyName(), currency.getCurrencyAbb(),
                currency.getRate());
    }

    @Override
    public Currency toEntity(CurrencyDto currencyDto) {
        return new Currency(currencyDto.getId(), currencyDto.getCurrencyName(),
                currencyDto.getCurrencyAbb(), currencyDto.getRate());
    }
}
