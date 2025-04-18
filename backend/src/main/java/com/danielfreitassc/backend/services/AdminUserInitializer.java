package com.danielfreitassc.backend.services;

import org.springframework.stereotype.Component;
import org.springframework.beans.factory.annotation.Value;

import com.danielfreitassc.backend.dtos.UserRequestDto;
import com.danielfreitassc.backend.models.UserRole;

import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class AdminUserInitializer {

    private final UserService userService;
    
    @Value("${spring.datasource.username}")
    private String adminEmail;
    
    @Value("${spring.datasource.password}")
    private String adminPassword;

    @PostConstruct
    public void init() {        
        if (!userService.existsByEmail(adminEmail)) {
            UserRequestDto adminUserDTO = new UserRequestDto("Administrado", adminEmail, adminPassword, UserRole.ADMIN);
            userService.create(adminUserDTO);
            System.out.println("Admin user created.");
        } else {
            System.out.println("Admin user already exists.");
        }
    }

}
