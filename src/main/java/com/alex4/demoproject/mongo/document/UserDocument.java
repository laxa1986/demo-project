package com.alex4.demoproject.mongo.document;

import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Document(collection = "user")
public class UserDocument {
    @Id
    private String id;

    @Field("name")
    private String name;

    // db.getCollection('user').createIndex( { "email": 1 }, { unique: true } )
    @Indexed(unique = true)
    private String email;

    // TODO: see https://www.baeldung.com/spring-data-mongodb-index-annotations-converter
    // TODO: + see about converters
    @PersistenceConstructor
    public UserDocument(String name, String email) {
        this.name = name;
        this.email = email;
    }
}
