package com.park.spring.security.mongodb.securitymongodb.service;

import com.park.spring.security.mongodb.securitymongodb.model.CustomUserDetails;
import com.park.spring.security.mongodb.securitymongodb.model.User;
import com.park.spring.security.mongodb.securitymongodb.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> optionalUser = userRepository.findByUsername(username);

        optionalUser.orElseThrow(() -> new UsernameNotFoundException("Username not found!!"));
        return optionalUser.map(CustomUserDetails::new).get();
    }
}
