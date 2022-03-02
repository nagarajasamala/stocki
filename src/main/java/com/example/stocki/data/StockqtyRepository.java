package com.example.stocki.data;

import org.springframework.data.repository.CrudRepository;
import com.example.stocki.model.Stockqty;
import com.example.stocki.model.Users;
import com.example.stocki.model.Products;


// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StockqtyRepository extends CrudRepository<Stockqty, Integer> {

	Iterable<Stockqty> findAllByUsers(Users user);
	Iterable<Stockqty> findAll();
    	Stockqty findByUsersAndProducts(Users u, Products p);

}
