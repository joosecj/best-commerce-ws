package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.AccountCredentialsDTO;
import com.github.bestcommerce.dtos.v1.TokenDTO;
import com.github.bestcommerce.entities.Permission;
import com.github.bestcommerce.repositories.UserRepository;
import com.github.bestcommerce.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @SuppressWarnings("rawtypes")
    public ResponseEntity signin(AccountCredentialsDTO accountCredentialsDTO) {
        try {
            var email = accountCredentialsDTO.getEmail();
            var password = accountCredentialsDTO.getPassword();
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(email, password));
            var user = userRepository.findByUserEmail(email);
            if (user == null) {
                throw new UsernameNotFoundException("Email " + email + " not found!");
            }
            List<String> roles = user.getPermissions().stream().map(Permission::getDescription).toList();
            TokenDTO tokenResponse = tokenProvider.createAccessToken(email, roles);
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid email/password supplied!");
        }

    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String email, String refreshToken) {
        var user = userRepository.findByUserEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email " + email + " not found!");
        }
        TokenDTO tokenResponse = tokenProvider.refreshToken(refreshToken);
        return ResponseEntity.ok(tokenResponse);
    }
}
