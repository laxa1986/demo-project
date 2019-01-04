package com.alex4.demoproject.mongo.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Document(collection = "user")
public class UserDocument {
    @Id
    private String id;
    private String name;
    private String email;

    public UserDocument(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
