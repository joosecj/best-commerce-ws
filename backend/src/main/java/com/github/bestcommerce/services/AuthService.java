package com.github.bestcommerce.services;

import com.github.bestcommerce.config.SecurityConfig;
import com.github.bestcommerce.dtos.v1.AccountCreatDTO;
import com.github.bestcommerce.dtos.v1.AccountCredentialsDTO;
import com.github.bestcommerce.dtos.v1.TokenDTO;
import com.github.bestcommerce.entities.Permission;
import com.github.bestcommerce.entities.User;
import com.github.bestcommerce.repositories.PermissionRepository;
import com.github.bestcommerce.repositories.UserRepository;
import com.github.bestcommerce.security.jwt.JwtTokenProvider;
import com.github.bestcommerce.services.exceptions.DataBaseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.List;

@Service
public class AuthService {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenProvider tokenProvider;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private SecurityConfig securityConfig;

    public TokenDTO signin(AccountCredentialsDTO accountCredentialsDTO) {
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
            return tokenProvider.createAccessToken(email, roles);
        } catch (Exception e) {
            throw new BadCredentialsException("Invalid email/password supplied!");
        }

    }

    public TokenDTO refreshToken(String email, String refreshToken) {
        var user = userRepository.findByUserEmail(email);
        if (user == null) {
            throw new UsernameNotFoundException("Email " + email + " not found!");
        }
        return tokenProvider.refreshToken(refreshToken);
    }

    @Transactional
    public void signup(AccountCreatDTO accountCreatDTO) {
        userRepository.save(copyDtoToEntity(accountCreatDTO));
    }

    private User copyDtoToEntity(AccountCreatDTO accountCreatDTO) {
        var userEntity = new User();
        userEntity.setName(accountCreatDTO.getName());
        userEntity.setEmail(accountCreatDTO.getEmail());
        userEntity.setPassword(hashAndSavePassword(accountCreatDTO.getPassword()));
        userEntity.setBirthDate(parseDate(accountCreatDTO.getBirthDate()));
        userEntity.setPhone(accountCreatDTO.getPhone());
        userEntity.setEnabled(true);
        userEntity.setAccountNonExpired(true);
        userEntity.setAccountNonLocked(true);
        userEntity.setCredentialsNonExpired(true);
        userEntity.setPermissions(grantClientPermission());
        return userEntity;
    }

    private String hashAndSavePassword(String password) {
        return securityConfig.passwordEncoder().encode(password);
    }

    private LocalDate parseDate(String dateString) {
        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(dateString, formatter);
            validateFutureDate(date);
        } catch (DateTimeParseException e) {
            throw new DataBaseException("Invalid date. Expected format: dd/MM/yyyy.");
        }
        return date;
    }

    private void validateFutureDate(LocalDate date) {
        LocalDate today = LocalDate.now();
        if (date.isAfter(today)) {
            throw new DataBaseException("Future date not allowed");
        }
    }

    private List<Permission> grantClientPermission() {
        List<Permission> permission = permissionRepository.findAll();
        return permission.stream().filter(p -> p.getDescription().equals("CLIENT")).toList();
    }
}
