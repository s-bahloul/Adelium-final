package com.adelium.web.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto
{
    private Long id;
    @NotNull
    private String firstname;
    @NotEmpty
    private String lastname;
    @NotEmpty(message = "Email should not be empty")
    @Email
    private String username;
    @NotEmpty(message = "Password should not be empty")
    private String password;
}