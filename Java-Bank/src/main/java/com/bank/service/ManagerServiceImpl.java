package com.bank.service;

import com.bank.domain.entity.Manager;
import com.bank.domain.exception.EntityNotFoundException;
import com.bank.repository.ManagerRepository;
import com.bank.repository.PersonalDataRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;

    private final PersonalDataRepository personalDataRepository;

    @Override
    public List<Manager> getAll() {
        List<Manager> managers = repository.findAll();
        if (managers.isEmpty()) {
            throw new EntityNotFoundException("Managers.");
        }

        return managers;
    }

    @Override
    public Manager getById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new EntityNotFoundException(String.format("Manager %d.", id)));
    }

    @Override
    public Manager create(Long personalDataId, Manager manager) {
        manager.setPersonalData(personalDataRepository.findById(personalDataId).orElseThrow(() ->
                new EntityNotFoundException(String.format("Personal Data %d.", personalDataId))));
        manager.setCreatedAt(LocalDateTime.now());

        return repository.save(manager);
    }

    @Override
    public Manager update(Long id, Manager manager) {
        Manager oldManager = getById(id);
        manager.setId(id);
        manager.setPersonalData(oldManager.getPersonalData());
        manager.setCreatedAt(oldManager.getCreatedAt());
        manager.setUpdatedAt(LocalDateTime.now());

        return repository.save(manager);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

    @Override
    public Manager changeStatus(Long id) {
        Manager manager = getById(id);
        manager.setStatus(!manager.isStatus());
        manager.setUpdatedAt(LocalDateTime.now());

        return repository.save(manager);
    }

    @Override
    public Manager getCurrent() {
        Manager manager = repository.findByEmail(
                SecurityContextHolder.getContext().getAuthentication().getName());

        if (manager == null) {
            throw new EntityNotFoundException("No assigned manager.");
        }

        return null;
    }
}
