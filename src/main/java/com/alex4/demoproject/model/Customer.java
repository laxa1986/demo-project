package com.alex4.demoproject.model;

import lombok.Getter;
import lombok.ToString;
import org.springframework.data.annotation.Id;

@Getter
@ToString
public class Customer {

    @Id
    private String id;
    private final String firstName;
    private final String lastName;

    /**
     * Should be exactly one constructor with all fields except id
     * - then spring data will use to to recreate objects when query DB
     */
    public Customer(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }
}