package com.preet.store.dto;

import com.preet.store.validation.LowercaseValidation;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateUserDto {

    @NotBlank(message = "Name is required.")
    String name;

    @LowercaseValidation(message = "Email must be in lowercase")
    @NotBlank(message = "Email is required.")
    @Email(message = "Email not correct.")
    String email;

    @NotBlank(message = "password is required.")
    String password;
}
