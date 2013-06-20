package com.javaheadbrain.experiments.spring.repositories;

import com.javaheadbrain.experiments.spring.domain.User;

import java.util.List;

/**
 * Stub repository.
 */
public interface UserRepository {

    List<User> findByUsername(String username);

    // Spring Data would define these for you
    User save(User user);

    User findOne(Long id);

    Iterable<User> findAll();

    void delete(User user);

    void delete(Long id);

}