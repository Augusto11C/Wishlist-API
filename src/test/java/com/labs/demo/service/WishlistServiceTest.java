package com.labs.demo.service;

import com.labs.demo.model.dto.WishlistDTO;
import com.labs.demo.model.persistence.User;
import com.labs.demo.model.persistence.repositories.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class WishlistServiceTest {

    private UserRepository userRepository;
    private WishlistService wishList;


    @BeforeEach
    public void setup() {
        userRepository = mock(UserRepository.class);

        wishList = new WishlistService(userRepository);
    }

    @Test
    public void testeSaveNewItem(){
        User user = new User();
        user.setUserName("teste");
        user.setLoginName("testeLogin");
        user.setWishlist(new ArrayList<>());

        WishlistDTO dto = new WishlistDTO("testeLogin", "item1");

        when(userRepository.findByLoginName(anyString())).thenReturn(user);

        List<String> strings = wishList.addToWishlist(dto);

        List<String> expectedList = Arrays.asList("ITEM1");

        Assertions.assertTrue(expectedList.equals(strings));
    }
}
