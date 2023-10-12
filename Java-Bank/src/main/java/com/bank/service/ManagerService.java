package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Manager;
import com.bank.domain.entity.PersonalData;

import java.util.List;

public interface ManagerService {

    public List<Manager> getAll();

    public Manager getById(long id);

    public Manager create(long personalDataId, Manager manager);

    public Manager update(long id, Manager manager);

    public void delete(long id);

    public void changeStatus(long id);

    public PersonalData getPersonalData(long id);
}