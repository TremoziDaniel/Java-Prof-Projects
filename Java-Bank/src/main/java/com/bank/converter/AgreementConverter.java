package com.bank.converter;

import com.bank.domain.dto.AgreementDto;
import com.bank.domain.entity.Agreement;
import org.springframework.stereotype.Component;

@Component
public class AgreementConverter implements EntityConverter<Agreement, AgreementDto>{

    @Override
    public AgreementDto toDto(Agreement agreement) {
        return new AgreementDto(agreement.getId(), agreement.getAccount().getIban(),
                agreement.getProduct().getId(), agreement.getInterestRate(),
                agreement.isStatus(), agreement.getCurrency().getCurrencyAbb(),
                agreement.getSum(), agreement.getCreatedAt(), agreement.getUpdatedAt());
    }

    @Override
    public Agreement toEntity(AgreementDto agreementDto) {
        return new Agreement(agreementDto.getId(), null, null,
                agreementDto.getInterestRate(), agreementDto.isStatus(), null,
                agreementDto.getSum(), agreementDto.getCreatedAt(), agreementDto.getUpdatedAt());
    }
}
