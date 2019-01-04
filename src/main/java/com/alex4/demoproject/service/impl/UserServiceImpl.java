package com.alex4.demoproject.service.impl;

import com.alex4.demoproject.model.User;
import com.alex4.demoproject.mongo.document.UserDocument;
import com.alex4.demoproject.mongo.repository.UserRepository;
import com.alex4.demoproject.service.UserService;
import com.mongodb.MongoClient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import static org.springframework.data.mongodb.core.query.Criteria.where;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    public UserServiceImpl(@Autowired UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Mono<String> createUser(User user) {
        var userDoc = new UserDocument(user.getName(), user.getEmail());
        return userRepository.save(userDoc).map(UserDocument::getId);
    }

    private void mongoTemplate() {
        MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "DemoDB");
        mongoOps.insert(new UserDocument("Joe", "fake@email.com"));
        var x = mongoOps.findOne(new Query(where("name").is("Joe")), UserDocument.class);
        log.info("" + x);
    }
}
