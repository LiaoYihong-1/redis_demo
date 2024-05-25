package com.example.videoBack.dao.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
public class AccountRequest {
    @NotNull(message = "Name cannot be null")
    @NotBlank
    @Size(min = 3, max = 50, message = "Name must be between 3 and 50 characters")
    private String name;
    @NotBlank
    @NotNull(message = "Password cannot be null")
    @Size(min = 6, max = 20, message = "Name must be between 6 and 20 characters")
    private String password;
    @NotNull(message = "Email cannot be null")
    @NotBlank
    @Pattern(regexp = "^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$", message = "Invalid email format")
    private String email;
    @NotNull(message = "Account type cannot be null")
    @NotBlank
    private String type;
}
