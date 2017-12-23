package com.webapi.newbie.repo;

import java.util.Optional;

import com.webapi.newbie.model.Account;

import org.springframework.data.repository.CrudRepository;

public interface AccountRepo extends CrudRepository<Account, Long> {
    Optional<Account> findByUsername(String username);
}