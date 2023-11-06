package com.bank.service;

import com.bank.domain.entity.PersonalData;

import java.util.List;

public interface PersonalDataService {

    List<PersonalData> getAll();

    PersonalData getById(Long id);

    PersonalData create(PersonalData personalData);

    PersonalData update(Long id, PersonalData personalData);

    void delete(Long id);
}
