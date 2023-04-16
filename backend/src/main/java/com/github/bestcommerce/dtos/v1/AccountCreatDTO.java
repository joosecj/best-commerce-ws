package com.github.bestcommerce.dtos.v1;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;

import java.util.Objects;

public class AccountCreatDTO {
    private String name;
    @Email(message = "email invalid")
    private String email;
    @NotNull(message = "required field")
    private String password;
    @NotNull(message = "required field")
    private String phone;
    @NotNull(message = "required field")
    private String birthDate;

    public AccountCreatDTO(String name, String email, String password, String phone, String birthDate) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.birthDate = birthDate;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getPhone() {
        return phone;
    }

    public String getBirthDate() {
        return birthDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountCreatDTO that = (AccountCreatDTO) o;
        return Objects.equals(email, that.email) && Objects.equals(password, that.password);
    }

    @Override
    public int hashCode() {
        return Objects.hash(email, password);
    }
}
