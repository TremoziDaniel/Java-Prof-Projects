package com.bank.service;

import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {

    private final PersonalDataRepository repository;

    @Override
    public List<PersonalData> getAll() {
        List<PersonalData> personalData =  repository.findAll();
        if (personalData.isEmpty()) {
            throw new EntityNotFoundException("PersonalData");
        }

        return personalData;
    }

    @Override
    public PersonalData getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Personal Data %d", id)));
    }

    @Override
    public PersonalData create(@Valid PersonalData personalData) {
        return repository.save(personalData);
    }

    @Override
    public PersonalData update(Long id, @Valid PersonalData personalData) {
        PersonalData oldPersonalData = getById(id);
        personalData.setId(oldPersonalData.getId());

        return repository.save(personalData);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}
