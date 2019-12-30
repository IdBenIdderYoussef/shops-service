package com.remote.shopsservice.model.dto;

import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDto {
    @NotEmpty
    @Email
    private String username;
    @NotEmpty
    private String password;
    @NotEmpty
    private String confirmationPassword;
}
