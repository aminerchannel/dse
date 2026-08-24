package com.example.demo;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record ConnectionRequest(

    @NotBlank
    @Pattern(regexp = "^[A-Za-z0-9._-]+$", message = "host contains illegal characters")
    String host,

    @Min(value = 1, message = "port must be between 1 and 65535")
    @Max(value = 65535, message = "port must be between 1 and 65535")
    int port,
    
    @NotBlank(message = "database is required")
    @Pattern(regexp = "^[A-Za-z0-9_-]+$", message = "database contains illegal characters")
    String database,

    @NotBlank(message = "username is required")
    String username,

    @NotBlank(message = "password is required")
    String password

){

}