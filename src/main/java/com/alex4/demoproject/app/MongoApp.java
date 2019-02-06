package com.alex4.demoproject.app;

import com.alex4.demoproject.rest.controller.UserController;
import com.alex4.demoproject.mongo.repository.UserRepository;
import com.alex4.demoproject.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

// TODO: examine documentation https://docs.spring.io/spring-data/mongodb/docs/current/reference/html/#get-started:first-steps:nosql
@EnableWebFlux
@EnableReactiveMongoRepositories(basePackageClasses = UserRepository.class)
@SpringBootApplication(scanBasePackageClasses = {UserServiceImpl.class, UserController.class})
public class MongoApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MongoApp.class, args);
    }

    @Value("${spring.application.name}")
    private String name;

    @Override
    public void run(String... args) {
        System.out.println("app name: " + name);
    }
}