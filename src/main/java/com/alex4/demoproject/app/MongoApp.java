package com.alex4.demoproject.app;

import com.alex4.demoproject.controller.UserController;
import com.alex4.demoproject.repository.CustomerRepository;
//import com.alex4.demoproject.repository2.ReactiveCustomerRepository;
import com.alex4.demoproject.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
//@EnableReactiveMongoRepositories(basePackageClasses = ReactiveCustomerRepository.class)
@EnableMongoRepositories(basePackageClasses = CustomerRepository.class)
@SpringBootApplication(scanBasePackageClasses = {CustomerService.class, UserController.class})
public class MongoApp implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(MongoApp.class, args);
    }

    private final CustomerService customerService;

    @Value("${spring.application.name}")
    private String name;

    public MongoApp(@Autowired CustomerService customerService) {
        this.customerService = customerService;
    }

    @Override
    public void run(String... args) {
        System.out.println("app name: " + name);
        customerService.doAll();
    }
}