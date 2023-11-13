package com.bank.repository;

import com.bank.domain.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    Manager findByManager_PersonalData_Email(String email);
}
