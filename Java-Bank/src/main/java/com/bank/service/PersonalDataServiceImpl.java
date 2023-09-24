package com.bank.service;

import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.PersonalDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonalDataServiceImpl implements PersonalDataService {

    @Autowired
    private PersonalDataRepository repository;

    @Override
    public List<PersonalData> getAll() {
        return repository.findAll();
    }

    @Override
    public PersonalData getById(long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidArgumentException("Info"));
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
}