package org.telran.validationExample.com.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.telran.validationExample.com.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
