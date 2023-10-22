package com.bank.service;

import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;
import com.bank.domain.exception.CannotBeCreatedException;
import com.bank.domain.exception.ItemNotFoundException;
import com.bank.repository.ManagerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ManagerServiceImpl implements ManagerService {

    private final ManagerRepository repository;

    @Autowired
    private PersonalDataService personalDataService;

    public ManagerServiceImpl(ManagerRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Manager> getAll() {
        List<Manager> managers = repository.findAll();
        if (managers.isEmpty()) {
            throw new ItemNotFoundException("Managers");
        }

        return managers;
    }

    @Override
    public Manager getById(long id) {
        return repository.findById(id).orElseThrow(() ->
                new ItemNotFoundException(String.format("Manager %d", id)));
    }

    @Override
    public Manager create(long personalDataId, Manager manager) {
        try {
            manager.setPersonalData(personalDataService.getById(personalDataId));
        } catch (ItemNotFoundException e) {
            throw new CannotBeCreatedException(String.format("Manager %s", manager.getId()), e);
        }

        return repository.save(manager);
    }

    @Override
    public Manager update(long id, Manager manager) {
        Manager oldManager = getById(id);
        manager.setId(id);
        manager.setPersonalData(oldManager.getPersonalData());

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
