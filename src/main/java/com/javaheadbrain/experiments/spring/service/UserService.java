package com.javaheadbrain.experiments.spring.service;

import com.javaheadbrain.experiments.spring.domain.User;

import java.util.List;

public interface UserService {

    public User create(User user);

    public User findByID(Long id);

    public List<User> findByUsername(String userName);

    public Iterable<User> findAll();

    public User update(User user);

    public void delete(User user);

    public void delete(Long id);

}

