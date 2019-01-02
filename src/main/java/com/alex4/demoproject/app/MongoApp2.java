package com.alex4.demoproject.app;

import com.alex4.demoproject.model.Person;
import com.mongodb.MongoClient;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Query;

import static org.springframework.data.mongodb.core.query.Criteria.where;

public class MongoApp2 {
    private static final Log log = LogFactory.getLog(MongoApp2.class);

    public static void main(String[] args) throws Exception {
        MongoOperations mongoOps = new MongoTemplate(new MongoClient(), "DemoDB");
        mongoOps.insert(new Person("Joe", 34));

        log.info(mongoOps.findOne(new Query(where("name").is("Joe")), Person.class));

//        mongoOps.dropCollection("person");
    }
}
