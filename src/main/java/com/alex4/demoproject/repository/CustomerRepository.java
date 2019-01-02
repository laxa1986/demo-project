package com.alex4.demoproject.repository;

import java.util.List;

import com.alex4.demoproject.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 *              / spring data jpa (for SQL)
 * spring data
 *              \ spring data mongodb (for MongoDB)
 */
public interface CustomerRepository extends MongoRepository<Customer, String> {
    Customer findByFirstName(String firstName);
    List<Customer> findByLastName(String lastName);
}