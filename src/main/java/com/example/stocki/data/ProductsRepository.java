
package com.example.stocki.data;

import org.springframework.data.repository.CrudRepository;

import com.example.stocki.model.Products;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface ProductsRepository extends CrudRepository<Products, Integer> {

	Products findByName(String name);
	Iterable<Products> findAll();

}
