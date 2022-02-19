
package com.example.stocki.data;

import org.springframework.data.repository.CrudRepository;

import com.example.stocki.model.Products;
import com.example.stocki.model.Users;
import com.example.stocki.model.Operations;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface OperationsRepository extends CrudRepository<Operations, Integer> {

	Iterable<Operations> findAllByUsers(Users s);
	Iterable<Operations> findAllByProducts(Products s);
	Iterable<Operations> findAllByUsersAndDateAndOp(Users u,String date,String op);





}
