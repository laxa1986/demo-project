package com.alex4.demoproject.controller;

import com.alex4.demoproject.controller.dto.UserDto;
import com.alex4.demoproject.model.User;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.concurrent.ForkJoinPool;

@RestController
@RequestMapping("/user")
public class UserController {

    @GetMapping("/get/{id}")
    public Mono<String> getUser(@PathVariable String id) {
        return Mono.just(id);
    }

    @GetMapping("/list")
    public Flux<User> list() {
        return Flux.empty();
    }

    @PostMapping("/create")
    public Mono<String> createUser(@RequestBody UserDto userDto) {
        System.out.println(userDto);
        return Mono.just("works");
    }
}
