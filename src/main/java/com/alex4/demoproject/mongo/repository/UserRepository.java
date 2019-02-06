package com.alex4.demoproject.mongo.repository;

import com.alex4.demoproject.mongo.document.UserDocument;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface UserRepository extends ReactiveCrudRepository<UserDocument, String> {
//    Customer findByFirstName(String firstName);
//
//    List<Customer> findByLastName(String lastName);

//    Mono<User> findByFirstName(String firstName);

//    @Query("{ 'firstname': ?0, 'lastname': ?1}")
//    Mono<Customer> findByFirstnameAndLastname(String firstname, String lastname);

    // TODO: https://github.com/spring-projects/spring-data-examples/tree/master/mongodb/reactive
}
