package com.bank.converter;

import com.bank.domain.dto.AgreementDto;
import com.bank.domain.entity.Agreement;
import com.bank.service.CurrencyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AgreementConverter implements EntityConverter<Agreement, AgreementDto>{

    @Autowired
    private CurrencyService currencyService;

    @Override
    public AgreementDto toDto(Agreement agreement) {
        return new AgreementDto(agreement.getId(), agreement.getAccount().getName(),
                agreement.getProduct().getName(), agreement.getInterestRate(),
                agreement.isStatus(), agreement.getCurrency().getCurrencyAbb(),
                agreement.getSum(), agreement.getCreatedAt(), agreement.getUpdatedAt());
    }

    @Override
    public Agreement toEntity(AgreementDto agreementDto) {
        return new Agreement(agreementDto.getId(), null, null,
                agreementDto.getInterestRate(), agreementDto.isStatus(),
                currencyService.getCurrencyByAbb(agreementDto.getCurrencyAbb()),
                agreementDto.getSum(), agreementDto.getCreatedAt(), agreementDto.getUpdatedAt());
    }
}