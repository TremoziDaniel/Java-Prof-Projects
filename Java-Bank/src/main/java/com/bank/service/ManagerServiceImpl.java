package com.bank.service;

import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.ManagerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;

    public ManagerServiceImpl(ManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manager> getAll() {
        return repository.findAll();
    }

    @Override
    public Manager getById(long id) {
        return repository.findById(id).orElseThrow(() -> new ItemNotFoundException("Manager"));
    }

    @Override
    public Manager create(Manager manager) {
        return repository.save(manager);
    }

    @Override
    public Manager update(long id, Manager manager) {
        manager.setId(id);

        return repository.save(manager);
    }

    @Override
    public void delete(long id) {
        repository.deleteById(id);
    }

    @Override
    public void changeStatus(long id) {
        Manager manager = getById(id);
        manager.setStatus(!manager.isStatus());
        repository.save(manager);
    }

    @Override
    public PersonalData getPersonalData(long id) {
        return getById(id).getPersonalData();
    }
}