package com.login.API_LOGIN.user;

import java.util.Collections;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import at.favre.lib.crypto.bcrypt.BCrypt;


@RestController
@RequestMapping("/users")
public class UserController {
    
    @Autowired
    private IUserRepository userRepository;

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/register")
    public ResponseEntity create(@RequestBody UserModel userModel){
        var id = this.userRepository.findById(userModel.getId());
        if(id != null){
            System.out.println("Usuário já existe!");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Usúario já existe!");
        }

        var passwordHash = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray());
        userModel.setPassword(passwordHash);
        var userCreated = this.userRepository.save(userModel);
         return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @CrossOrigin(origins = "*", allowedHeaders = "*")
    @PostMapping("/")
    public ResponseEntity<Map<String, String>> login(@RequestBody UserModel userModel) {
    var user = this.userRepository.findById(userModel.getId());

    if (user == null || !BCrypt.verifyer().verify(userModel.getPassword().toCharArray(), user.getPassword()).verified) {
        System.out.println("Usuário ou senha inválidos");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(Collections.singletonMap("error", "Usuário ou senha inválidos"));
    }

    return ResponseEntity.ok(Collections.singletonMap("message", "Login bem-sucedido"));
}


    
}