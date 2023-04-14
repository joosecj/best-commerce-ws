package com.github.bestcommerce.services.exceptions;

import org.springframework.security.core.AuthenticationException;

public class InvalidJwtAuthenticationException extends AuthenticationException {
    public InvalidJwtAuthenticationException(String ex) {
        super(ex);
    }
}
