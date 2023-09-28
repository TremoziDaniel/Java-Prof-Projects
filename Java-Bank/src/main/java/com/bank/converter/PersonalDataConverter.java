package com.bank.converter;

import com.bank.domain.dto.PersonalDataDto;
import com.bank.domain.entity.PersonalData;
import org.springframework.stereotype.Component;

@Component
public class PersonalDataConverter implements EntityConverter<PersonalData, PersonalDataDto>{

    @Override
    public PersonalDataDto toDto(PersonalData personalData) {
        return new PersonalDataDto(personalData.getId(), personalData.getFirstName(),
                personalData.getLastName(), personalData.getCountry(), personalData.getCity(),
                personalData.getStreet(), personalData.getHouseNumber(), personalData.getApartmentNumber(),
                null, null, null);
    }

    @Override
    public PersonalData toEntity(PersonalDataDto personalDataDto) {
        return new PersonalData(personalDataDto.getId(), personalDataDto.getFirstName(),
                personalDataDto.getLastName(), personalDataDto.getCountry(), personalDataDto.getCity(),
                personalDataDto.getStreet(), personalDataDto.getHouseNumber(),
                personalDataDto.getApartmentNumber(), personalDataDto.getPhoneNumber(),
                personalDataDto.getEmail(), personalDataDto.getPassword());
    }
}