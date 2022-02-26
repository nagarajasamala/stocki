package com.example.stocki.data;

import org.springframework.data.repository.CrudRepository;
import com.example.stocki.model.Stockqty;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface StockqtyRepository extends CrudRepository<Stockqty, Integer> {

	Iterable<Stockqty> findAllByUserId(Integer id);
	Iterable<Stockqty> findAll();

}