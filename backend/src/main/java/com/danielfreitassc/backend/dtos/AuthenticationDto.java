package com.danielfreitassc.backend.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;

public record AuthenticationDto(
    @Pattern(regexp = "^[^@\\s]+@[^@\\s]+\\.[^@\\s]+$", message = "Email inválido")
    @NotBlank(message = "Email não pode estar vazio")
    String email,
    @NotBlank(message = "Senha não pode estar vazia")
    String password
) {
    
}
