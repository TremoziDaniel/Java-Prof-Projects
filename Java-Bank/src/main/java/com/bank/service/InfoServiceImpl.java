package com.bank.service;

import com.bank.domain.entity.Info;
import com.bank.domain.exception.InvalidArgumentException;
import com.bank.repository.InfoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InfoServiceImpl implements InfoService {

    @Autowired
    private InfoRepository repository;

    @Override
    public List<Info> getAll() {
        return repository.findAll();
    }

    @Override
    public Info getById(Long id) {
        return repository.findById(id).orElseThrow(() -> new InvalidArgumentException("Info"));
    }

    @Override
    public Info create(Info info) {
        return repository.save(info);
    }

    @Override
    public Info update(Long id, Info info) {
        info.setId(id);

        return repository.save(info);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }
}