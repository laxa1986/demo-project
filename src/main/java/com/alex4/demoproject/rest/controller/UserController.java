package com.alex4.demoproject.rest.controller;

import com.alex4.demoproject.rest.dto.UserDto;
import com.alex4.demoproject.model.User;
import com.alex4.demoproject.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/user")
public class UserController {
    private UserService userService;

    public UserController(@Autowired UserService userService) {
        this.userService = userService;
    }

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
        var user = new User(userDto.getName(), userDto.getEmail());
        return userService.createUser(user);
    }
}
