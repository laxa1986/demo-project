package com.alex4.demoproject.service;

import com.alex4.demoproject.app.MongoApp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Deprecated
public class CustomerServiceImpl {
    private static final Logger log = LoggerFactory.getLogger(MongoApp.class);

    private CustomerRepository repository;

    @Transactional
    public void doAll() {
//        Mono<Customer> mono = reactiveRepository.findByFirstName("Alice");
//        mono.subscribe(c -> {
//            System.out.println(Thread.currentThread().getName());
//            System.out.println(c);
//        });
//        mono.doOnError(ex -> {
//            System.out.println(ex);
//        });
//        System.out.println(mono);
//        try {
//            Thread.sleep(2000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }

        repository.deleteAll();

        // save a couple of customers
        repository.save(new Customer("Alice", "Smith"));
        repository.save(new Customer("Bob", "Smith"));
        long count = repository.count();
        System.out.println("count: " + count);

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

    static class Customer {

        @Id
        private String id;
        private final String firstName;
        private final String lastName;

        /**
         * Should be exactly one constructor with all fields except id
         * - then spring data will use to to recreate objects when query DB
         */
        Customer(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }
    }

    interface CustomerRepository extends MongoRepository<Customer, String> {
        Customer findByFirstName(String firstName);
        List<Customer> findByLastName(String lastName);
    }
}
