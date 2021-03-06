package com.park.spring.security.jwt.mongo.securityjwtmongodb.security;

import com.park.spring.security.jwt.mongo.securityjwtmongodb.PersonRepository.PersonRepository;
import com.park.spring.security.jwt.mongo.securityjwtmongodb.model.CustomPersonDetails;
import com.park.spring.security.jwt.mongo.securityjwtmongodb.model.JwtAuthenticationToken;
import com.park.spring.security.jwt.mongo.securityjwtmongodb.model.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.AbstractUserDetailsAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
public class JwtAuthenticationProvider extends AbstractUserDetailsAuthenticationProvider {

    @Autowired
    private JwtValidator validator;

    @Autowired
    private PersonRepository personRepository;

    @Override
    protected void additionalAuthenticationChecks(UserDetails userDetails, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

    }

    @Override
    protected UserDetails retrieveUser(String username, UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken) throws AuthenticationException {

        JwtAuthenticationToken jwtAuthenticationToken = (JwtAuthenticationToken) usernamePasswordAuthenticationToken;
        String token = jwtAuthenticationToken.getToken();

        Person personClient = validator.validate(token);
        Person personDB = personRepository.findByUsername(personClient.getUsername());

        if (personClient == null) {
            throw new RuntimeException("JWT Token is incorrect");
        }

        return new CustomPersonDetails(personClient,personDB);
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return (JwtAuthenticationToken.class.isAssignableFrom(aClass));
    }
}
