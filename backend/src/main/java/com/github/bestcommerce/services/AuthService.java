package com.github.bestcommerce.services;

import com.github.bestcommerce.dtos.v1.AccountCreatDTO;
import com.github.bestcommerce.dtos.v1.AccountCredentialsDTO;
import com.github.bestcommerce.dtos.v1.TokenDTO;
import com.github.bestcommerce.entities.Permission;
import com.github.bestcommerce.entities.User;
import com.github.bestcommerce.repositories.PermissionRepository;
import com.github.bestcommerce.repositories.UserRepository;
import com.github.bestcommerce.security.jwt.JwtTokenProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.DelegatingPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
        Map<String, PasswordEncoder> encoders = new HashMap<>();

        Pbkdf2PasswordEncoder pbkdf2Encoder =
                new Pbkdf2PasswordEncoder(
                        "", 8, 185000,
                        Pbkdf2PasswordEncoder.SecretKeyFactoryAlgorithm.PBKDF2WithHmacSHA256);

        encoders.put("pbkdf2", pbkdf2Encoder);
        DelegatingPasswordEncoder passwordEncoder = new DelegatingPasswordEncoder("pbkdf2", encoders);
        passwordEncoder.setDefaultPasswordEncoderForMatches(pbkdf2Encoder);
        return passwordEncoder.encode(password);
    }

    private LocalDate parseDate(String dateString) {
        LocalDate date;
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
            date = LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            throw new IllegalArgumentException("Data inválida. Formato esperado: dd/MM/yyyy.", e);
        }
        return date;
    }

    private List<Permission> grantClientPermission() {
        List<Permission> permission = permissionRepository.findAll();
        return permission.stream().filter(p -> p.getDescription().equals("CLIENT")).toList();
    }
}
