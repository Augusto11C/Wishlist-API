package com.labs.demo.model.persistence.repositories;

import com.labs.demo.model.persistence.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UserRepository extends MongoRepository<User, String> {
    User findByUserName(String username);

    User findByLoginName(String username);
}
