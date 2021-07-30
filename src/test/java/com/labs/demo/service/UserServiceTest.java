package com.labs.demo.service;

import com.labs.demo.model.dto.UserDTO;
import com.labs.demo.model.persistence.User;
import com.labs.demo.model.persistence.repositories.UserRepository;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.verification.VerificationMode;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    private UserService userService;
    private BCryptPasswordEncoder bCryptPasswordEncoder;
    private UserRepository userRepository;

    @BeforeEach
    public void setup() {
        bCryptPasswordEncoder = mock(BCryptPasswordEncoder.class);
        userRepository = mock(UserRepository.class);

        userService = new UserService(userRepository, bCryptPasswordEncoder);
    }

    @Test
    public void testCreateUser() {

        UserDTO userDTO = new UserDTO(null, "teste", "teste2", "1234567890", "1234567890");

        when(userRepository.findByLoginName(anyString())).thenReturn(null);
        when(bCryptPasswordEncoder.encode(any())).thenReturn("encodedPassword");

        userService.createUser(userDTO);

        verify(userRepository).findByLoginName("teste2");
    }

    @Test
    public void testCreateWithDuplicatedLoginName() {

        UserDTO userDTO = new UserDTO(null, "teste", "teste2", "1234567890", "1234567890");

        when(userRepository.findByLoginName(anyString())).thenReturn(new User());
        assertThrows(
                RuntimeException.class,
                () -> userService.createUser(userDTO));

        verify(userRepository).findByLoginName("teste2");
        verify(userRepository, times(0)).save(any());
    }
}
