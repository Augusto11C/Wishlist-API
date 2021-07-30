package com.labs.demo.security;

import com.labs.demo.model.persistence.User;
import com.labs.demo.model.persistence.repositories.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String loginName) throws UsernameNotFoundException {
        User user = userRepository.findByLoginName(loginName);
        if (user == null) {
            throw new UsernameNotFoundException(loginName);
        }
        return new org.springframework.security.core.userdetails.User(user.getLoginName(), user.getPassword(), Collections.emptyList());
    }
}
