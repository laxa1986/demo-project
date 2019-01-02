package com.alex4.demoproject.service;

import com.alex4.demoproject.app.MongoApp;
import com.alex4.demoproject.model.Customer;
import com.alex4.demoproject.repository.CustomerRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    private static final Logger log = LoggerFactory.getLogger(MongoApp.class);

    private final CustomerRepository repository;

    public CustomerServiceImpl(@Autowired CustomerRepository repository) {
        this.repository = repository;
    }

    @Override
    public void doAll() {
        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));

        // fetch all customers
        log.info("Customers found with findAll():");
        log.info("-------------------------------");
        for (Customer customer : repository.findAll()) {
            log.info(""+customer);
        }

        // fetch an individual customer
        log.info("\nCustomer found with findByFirstName('Alice'):");
        log.info("--------------------------------");
        log.info(""+repository.findByFirstName("Alice"));

        log.info("Customers found with findByLastName('Smith'):");
        log.info("--------------------------------");
        for (Customer customer : repository.findByLastName("Smith")) {
            log.info(""+customer);
        }
    }
}
