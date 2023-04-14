package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.AccountCredentialsDTO;
import com.github.bestcommerce.dtos.v1.TokenDTO;
import com.github.bestcommerce.repositories.UserRepository;
import com.github.bestcommerce.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

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

            var tokenResponse = new TokenDTO();
            if (user != null) {
                tokenResponse = tokenProvider.createAccessToken(email, user.getRoles());
            } else {
                throw new UsernameNotFoundException("Email " + email + " not found!");
            }
            return ResponseEntity.ok(tokenResponse);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid username/password supplied!");
        }
    }

    @SuppressWarnings("rawtypes")
    public ResponseEntity refreshToken(String email, String refreshToken) {
        var user = userRepository.findByUserEmail(email);

        var tokenResponse = new TokenDTO();
        if (user != null) {
            tokenResponse = tokenProvider.refreshToken(refreshToken);
        } else {
            throw new UsernameNotFoundException("Email " + email + " not found!");
        }
        return ResponseEntity.ok(tokenResponse);
    }
}
