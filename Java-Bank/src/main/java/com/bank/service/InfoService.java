package com.bank.service;

import com.bank.domain.entity.Info;

import java.util.List;

public interface InfoService {

    public List<Info> getAll();

    public Info getById(Long id);

    public Info create(Info info);

    public Info update(Long id, Info info);

    public void delete(Long id);
}