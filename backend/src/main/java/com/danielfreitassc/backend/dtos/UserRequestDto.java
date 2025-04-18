package com.danielfreitassc.backend.dtos;

import com.danielfreitassc.backend.configuration.OnCreate;
import com.danielfreitassc.backend.models.UserRole;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record UserRequestDto(
    @NotBlank(groups = OnCreate.class, message = "Nome não pode ser um campo em branco.") 
    String name,

    @Pattern(groups = OnCreate.class ,regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email inválido")
    @NotBlank(groups = OnCreate.class, message = "Email não pode ser um campo em branco") 
    String email,
    
    @NotBlank(groups = OnCreate.class, message = "Senha não pode ser um campo em branco") 
    String password,

    @NotNull(groups = OnCreate.class, message = "Cargo de segurança não pode ser um campo em branco")
     UserRole role
) {
    
}
