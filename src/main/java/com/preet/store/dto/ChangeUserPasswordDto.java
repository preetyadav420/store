package com.preet.store.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ChangeUserPasswordDto {
    private String oldPassword;
    private String newPassword;
}
