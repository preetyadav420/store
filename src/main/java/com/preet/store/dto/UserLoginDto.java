package com.preet.store.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class UserLoginDto {

    @NotBlank(message = "Email is required.")
    @Email(message = "Email is not correct.")
    private String email;

    @NotBlank(message = "Password is required.")
    private String password;
}
