package com.github.bestcommerce.services;

import com.github.bestcommerce.entities.User;
import com.github.bestcommerce.repositories.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userEmail) throws UsernameNotFoundException {
        var user = userRepository.findByUserEmail(userEmail);
        if (user != null) {
            return user;
        } else {
            throw new UsernameNotFoundException("Username " + userEmail + " not found!");
        }
    }

    protected User authentiated() {
        try {
            String username = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByUserEmail(username);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
