package com.bank.service;

import com.bank.domain.entity.Manager;

import java.util.List;

public interface ManagerService {

    List<Manager> getAll();

    Manager getById(Long id);

    Manager create(Long personalDataId, Manager manager);

    Manager update(Long id, Manager manager);

    void delete(Long id);

    Manager changeStatus(Long id);

    Manager getCurrent();
}
