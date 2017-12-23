package com.webapi.newbie.repo;

import org.springframework.data.repository.CrudRepository;
import com.webapi.newbie.model.User;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepo extends CrudRepository<User, Integer> {

}