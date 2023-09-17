package com.bank.service;

import com.bank.domain.entity.Account;
import com.bank.domain.entity.Manager;

import java.util.List;

public interface ManagerService {

    public List<Manager> getAll();

    public Manager getById(long id);

    public Manager create(Manager manager);

    public Manager update(long id, Manager manager);

    public List<Account> getAccounts(long id);

    public void delete(long id);
}