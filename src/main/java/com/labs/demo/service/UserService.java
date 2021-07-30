package com.labs.demo.service;

import com.labs.demo.model.dto.UserDTO;
import com.labs.demo.model.persistence.User;
import com.labs.demo.model.persistence.repositories.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserService.class);

    private UserRepository userRepository;
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public Optional<User> findByUserName(final String loginName) {
        final User user = userRepository.findByLoginName(loginName);
        return Optional.ofNullable(user);
    }

    public Optional<User> createUser(final UserDTO createUserRequest) {

        User user = new User();
        user.setUserName(createUserRequest.getUsername());
        user.setLoginName(createUserRequest.getLoginName());
        user.setWishlist(new ArrayList<>());

        validateLoginName(createUserRequest.getLoginName());


        if (createUserRequest.getPassword().length() < 8 || !createUserRequest.getPassword().equals(createUserRequest.getConfirmPassword())) {
            LOGGER.error("Event 'createUser', user password error: creating user={} failed.", createUserRequest.getUsername());
            return Optional.empty();
        }

        user.setPassword(bCryptPasswordEncoder.encode(createUserRequest.getPassword()));

        userRepository.save(user);

        LOGGER.info("Event 'createUser' successfully finished: item added to cart for user={}", createUserRequest.getUsername());
        return Optional.of(user);
    }

    private void validateLoginName(String loginName) {
        final User user = userRepository.findByLoginName(loginName);

        if(Objects.nonNull(user)) {
            throw new RuntimeException("LoginName ja esta sendo utilizado");
        }
    }

}
