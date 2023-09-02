package org.telran.validationExample.com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.telran.validationExample.com.entity.User;
import org.telran.validationExample.com.repository.UserRepository;


import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository repository;

    @Override
    public List<User> getAll() {
        return repository.findAll();
    }

    @Override
    public User addUser(User user) {
        return repository.save(user);
    }
}
