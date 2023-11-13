package com.bank.repository;

import com.bank.domain.entity.Manager;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ManagerRepository extends JpaRepository<Manager, Long> {

    @Query(value = "SELECT m FROM Manager m WHERE m.personalData.email = ?1")
    Manager findByEmail(String email);
}
