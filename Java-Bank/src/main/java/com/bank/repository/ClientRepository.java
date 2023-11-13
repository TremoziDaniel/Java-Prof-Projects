package com.bank.repository;

import com.bank.domain.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface ClientRepository extends JpaRepository<Client, UUID> {

    Optional<Client> findByTaxCode(String taxCode);

    Client findByClient_PersonalData_Email(String email);
}
