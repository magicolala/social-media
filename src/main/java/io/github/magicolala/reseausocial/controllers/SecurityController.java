package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class SecurityController {

    private final UserService     userService;
    private final PasswordEncoder passwordEncoder;

    @PostMapping(value = "/register")
    public ResponseEntity<User> register(@RequestBody User user) {

        try {
            user.setRole(User.Role.USER); // L'utilisateur enregistré a le rôle USER
            user.setPassword(passwordEncoder.encode(user.getPassword())); // Mot de passe chiffré

            User _user = userService.save(user);

            return new ResponseEntity<>(_user, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
