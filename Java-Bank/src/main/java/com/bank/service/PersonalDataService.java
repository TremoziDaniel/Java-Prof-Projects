package com.bank.service;

import com.bank.domain.entity.PersonalData;

import java.util.List;

public interface PersonalDataService {

    public List<PersonalData> getAll();

    public PersonalData getById(long id);

    public PersonalData create(PersonalData personalData);

    public PersonalData update(long id, PersonalData personalData);

    public void delete(long id);
}