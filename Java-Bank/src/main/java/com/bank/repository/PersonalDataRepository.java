package com.bank.repository;

import com.bank.domain.entity.PersonalData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonalDataRepository extends JpaRepository<PersonalData, Long> {
    // TODO find methods
}
