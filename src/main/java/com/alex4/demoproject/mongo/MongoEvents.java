package com.alex4.demoproject.mongo;

import org.springframework.data.domain.AfterDomainEventPublication;
import org.springframework.data.domain.DomainEvents;

import java.util.Collection;
import java.util.List;

public class MongoEvents {
    @DomainEvents
    Collection<Object> domainEvents() {
        // … return events you want to get published here
        return List.of();
    }

    @AfterDomainEventPublication
    void callbackMethod() {
        // … potentially clean up domain events list
    }
}
