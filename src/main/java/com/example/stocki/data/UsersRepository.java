package com.example.stocki.data;

import org.springframework.data.repository.CrudRepository;

import com.example.stocki.model.Users;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UsersRepository extends CrudRepository<Users, Integer> {

Users findByName(String name);

}
