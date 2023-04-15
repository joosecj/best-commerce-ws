package com.github.bestcommerce.services;

import com.github.bestcommerce.entities.Permission;
import com.github.bestcommerce.entities.User;
import com.github.bestcommerce.repositories.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

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
            return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(),
                    getAuthorities(user.getPermissions()));
        } else {
            throw new UsernameNotFoundException("Username " + userEmail + " not found!");
        }
    }

    private Collection<? extends GrantedAuthority> getAuthorities(List<Permission> roles) {
        return roles.stream().map(role ->
                        new SimpleGrantedAuthority("ROLE_" + role.getDescription()
                                .toUpperCase())).toList();
    }

    protected User authenticated() {
        try {
            String email = SecurityContextHolder.getContext().getAuthentication().getName();
            return userRepository.findByUserEmail(email);
        } catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }
}
