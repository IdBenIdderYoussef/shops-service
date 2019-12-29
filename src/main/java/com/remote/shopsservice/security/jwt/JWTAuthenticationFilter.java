package com.remote.shopsservice.security.jwt;

import com.auth0.jwt.JWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.remote.shopsservice.model.User;
import com.remote.shopsservice.repository.UserRepository;
import org.springframework.http.MediaType;
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
import java.util.Date;

import static com.auth0.jwt.algorithms.Algorithm.HMAC512;
import static com.remote.shopsservice.security.SecurityConstants.*;


public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    private UserRepository userRepository;

    private AuthenticationManager authenticationManager;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager ,
                                    UserRepository userRepository) {
        this.authenticationManager = authenticationManager;
        this.userRepository = userRepository;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest req,
                                                HttpServletResponse res) throws AuthenticationException {
        try {
            User credentials = new ObjectMapper().readValue(req.getInputStream(), User.class);

            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            credentials.getUsername(),
                            credentials.getPassword())
            );
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest req,
                                            HttpServletResponse res,
                                            FilterChain chain,
                                            Authentication auth) throws IOException, ServletException {

        String token = JWT.create()
                .withClaim("user_id", ((User) auth.getPrincipal()).getId())
                .withSubject(((User) auth.getPrincipal()).getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .sign(HMAC512(SECRET.getBytes()));

        res.addHeader(HEADER_STRING, TOKEN_PREFIX + token);
        res.setContentType(MediaType.APPLICATION_JSON_VALUE);
        res.getWriter().write("{\"token\": \"" + token + "\"}");
    }
}