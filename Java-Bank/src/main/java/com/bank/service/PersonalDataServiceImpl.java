package com.bank.service;

import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PersonalDataServiceImpl implements PersonalDataService {

    private final PersonalDataRepository repository;

    private final PasswordEncoder passwordEncoder;

    @Override
    public List<PersonalData> getAll() {
        List<PersonalData> personalData =  repository.findAll();
        if (personalData.isEmpty()) {
            throw new EntityNotFoundException("PersonalData.");
        }

        return personalData;
    }

    @Override
    public PersonalData getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Personal Data %d.", id)));
    }

    @Override
    public PersonalData create(@Valid PersonalData personalData) {
        personalData.setPassword(passwordEncoder.encode(personalData.getPassword()));

        return repository.save(personalData);
    }

    @Override
    public PersonalData update(Long id, @Valid PersonalData personalData) {
        PersonalData oldPersonalData = getById(id);
        personalData.setId(oldPersonalData.getId());
        personalData.setPassword(passwordEncoder.encode(personalData.getPassword()));

        return repository.save(personalData);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public List<String> getProtectedData(Long id) {
        PersonalData personalData = getById(id);

        return Arrays.asList(
                personalData.getPhoneNumber(), personalData.getEmail(), personalData.getPassword());
    }

    @Override
    public PersonalData getCurrentPersonalData() {
        return repository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName())
                .orElseThrow(() -> new EntityNotFoundException("You don't have assigned personal data."));
    }
}
