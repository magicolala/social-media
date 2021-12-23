package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.Unit;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.service.UnitService;
import io.github.magicolala.reseausocial.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/admin")
@RequiredArgsConstructor
public class AdminController {

    private final UserService userService;
    private final UnitService unitService;

    @PutMapping("/deactivate-user/{id}")
    public ResponseEntity<User> deactivateUser(@PathVariable Long id) {
        Optional<User> user = Optional.ofNullable(userService.getById(id));

        if (user.isPresent()) {
            User _user = user.get();
            _user.setActive(false);

            return new ResponseEntity(userService.save(_user), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/deactivate-unit/{id}")
    public ResponseEntity deactivateUnit(@PathVariable Long id) {
        Optional<Unit> unit = (Optional<Unit>) unitService.getById(id);

        if (unit.isPresent()) {
            Unit _unit = unit.get();
            _unit.setActive(false);

            return new ResponseEntity(unitService.save(_unit), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
