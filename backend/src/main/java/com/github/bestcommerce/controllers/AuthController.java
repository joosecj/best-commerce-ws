package com.github.bestcommerce.controllers;

import com.github.bestcommerce.dtos.v1.AccountCreatDTO;
import com.github.bestcommerce.dtos.v1.AccountCredentialsDTO;
import com.github.bestcommerce.dtos.v1.TokenDTO;
import com.github.bestcommerce.services.AuthService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth", description = "Authentication Endpoint")
public class AuthController {
    @Autowired
    private AuthService authService;

    @PostMapping(value = "/signin")
    @Operation(summary = "Signin", description = "Signin and return token",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = TokenDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<TokenDTO> signin(@Valid @RequestBody AccountCredentialsDTO accountCredentialsDTO) {
        var token = authService.signin(accountCredentialsDTO);
        return ResponseEntity.ok().body(token);
    }

    @PostMapping(value = "/signup")
    @Operation(summary = "Signup", description = "Signup",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = TokenDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<String> signup(@Valid @RequestBody AccountCreatDTO accountCreatDTO) {
        authService.signup(accountCreatDTO);
        return ResponseEntity.ok("Registration completed successfully!");
    }

    @PutMapping(value = "/refresh/{email}")
    @Operation(summary = "Refresh token", description = "Refresh token for authenticated user and returns a token",
            tags = {"Auth"},
            responses = {
                    @ApiResponse(description = "Success", responseCode = "200",
                            content = {
                                    @Content(
                                            mediaType = "application/json",
                                            array = @ArraySchema(schema = @Schema(implementation = TokenDTO.class))
                                    )
                            }),
                    @ApiResponse(description = "Bad Request", responseCode = "400", content = @Content),
                    @ApiResponse(description = "Unauthorized", responseCode = "401", content = @Content),
                    @ApiResponse(description = "Not Found", responseCode = "404", content = @Content),
                    @ApiResponse(description = "Internal Error", responseCode = "500", content = @Content),
            }
    )
    public ResponseEntity<TokenDTO> refreshToken(@PathVariable("email") String email,
                                       @RequestHeader("Authorization") String refreshToken) {
        var token = authService.refreshToken(email, refreshToken);
        return ResponseEntity.ok().body(token);
    }
}
