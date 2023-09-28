package com.bank.service;

import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.PersonalDataRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    private final PersonalDataRepository repository;

    public PersonalDataServiceImpl(PersonalDataRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<PersonalData> getAll() {
        return repository.findAll();
    }

    @Override
    public PersonalData getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Info"));
    }

    @Override
    public PersonalData create(PersonalData personalData) {
        return repository.save(personalData);
    }

    @Override
    public PersonalData update(long id, PersonalData personalData) {
        personalData.setId(id);

        return repository.save(personalData);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public String getPhoneNumber(long id) {
        return getById(id).getPhoneNumber();
    }

    @Override
    public String getEmail(long id) {
        return getById(id).getEmail();
    }

    @Override
    public String getPassword(long id) {
        return getById(id).getPassword();
    }
}