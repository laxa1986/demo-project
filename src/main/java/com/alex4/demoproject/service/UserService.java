package com.alex4.demoproject.service;

import com.alex4.demoproject.model.User;
import reactor.core.publisher.Mono;

public interface UserService {
    Mono<String> createUser(User user);
}
