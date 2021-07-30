package com.labs.demo.controller;


import com.labs.demo.model.dto.UserDTO;
import com.labs.demo.model.persistence.User;
import com.labs.demo.model.request.CreateUserRequest;
import com.labs.demo.model.request.UserResponse;
import com.labs.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;


@RestController
@RequestMapping("/api/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{username}")
    public ResponseEntity<UserResponse> findByUserName(@PathVariable String username) {
        Optional<User> userOptional = userService.findByUserName(username);

        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            return ResponseEntity.ok(UserResponse.toResponse(user.getId(), user.getLoginName()));
        }

        return ResponseEntity.badRequest().build();
    }

    @PostMapping("/create")
    public ResponseEntity<UserResponse> createUser(@RequestBody CreateUserRequest createUserRequest) {
        LOGGER.info("Event 'createUser', requested for userName={}", createUserRequest.getUserName());

        Optional<User> userOptional = userService.createUser(UserDTO.toDTO(
                null,
                createUserRequest.getUserName(),
                createUserRequest.getLoginName(),
                createUserRequest.getPassword(),
                createUserRequest.getConfirmPassword()

        ));

        if (userOptional.isPresent()) {
            final User user = userOptional.get();
            return ResponseEntity.ok(UserResponse.toResponse(user.getId(), user.getLoginName()));
        }
        return ResponseEntity.badRequest().build();
    }


}
