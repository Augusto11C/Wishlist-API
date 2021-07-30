package com.labs.demo.security;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.labs.demo.model.persistence.User;
import com.labs.demo.model.request.UserLogin;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.labs.demo.security.SecurityConstants.EXPIRATION_TIME;
import static com.labs.demo.security.SecurityConstants.HEADER_STRING;
import static com.labs.demo.security.SecurityConstants.SECRET;
import static com.labs.demo.security.SecurityConstants.TOKEN_PREFIX;


/**
 * This custom class is responsible for the authentication process. This class extends the UsernamePasswordAuthenticationFilter class,
 * which is available under both spring-security-web and spring-boot-starter-web dependency. The Base class parses
 * the user credentials (username and a password).
 * */
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private AuthenticationManager authenticationManager;

    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /**
     * It performs actual authentication by parsing the user credentials.
     */
    @Override
    public Authentication attemptAuthentication(HttpServletRequest req, HttpServletResponse res) throws AuthenticationException {
        try {
            final UserLogin credentials = new ObjectMapper().readValue(req.getInputStream(), UserLogin.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getLoginName(),
                            credentials.getPassword(),
                            new ArrayList<>()
                    ));

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * This method will be called after a user logs in successfully. Below, it is generating a String token (JWT)
     * for this user.
     */
    @Override
    protected void successfulAuthentication(
            HttpServletRequest req,
            HttpServletResponse res,
            FilterChain chain,
            Authentication auth
    ) throws IOException, ServletException {

        String token = JWT.create()
                .withSubject(((org.springframework.security.core.userdetails.User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
    }

}
