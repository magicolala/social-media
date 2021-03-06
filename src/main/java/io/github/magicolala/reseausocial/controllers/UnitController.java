package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.Adhere;
import io.github.magicolala.reseausocial.entity.Unit;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.service.AdhereService;
import io.github.magicolala.reseausocial.service.UnitService;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/unit")
@RequiredArgsConstructor
public class UnitController {

    @Qualifier(value = "Unit")
    private final UnitService   unitService;
    private final AdhereService adhereService;
    private final UserUtil      userUtil;

    @GetMapping("/{id}")
    public ResponseEntity<Unit> getUnitById(@PathVariable(value = "id") Long id) {
        Optional<Unit> unit = (Optional<Unit>) unitService.getById(id);

        return unit.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping()
    public ResponseEntity<Unit> saveUnit(@RequestBody Unit unit) {

        try {
            List<User> admins = new ArrayList<>();
            User       admin  = userUtil.getCurrentUser();
            admins.add(admin);
            unit.setAdmins(admins);
            Unit _unit = (Unit) unitService.save(unit);

            return new ResponseEntity<>(_unit, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Unit>> listUnit() {

        try {
            List<Unit> units;
            units = unitService.listAll();

            if (units.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(units, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Unit> updateUnit(@RequestBody Unit unit, @PathVariable Long id) {
        Optional<Unit> unitAModifier = (Optional<Unit>) unitService.getById(id);

        if (unitAModifier.isPresent()) {
            Unit _unit = unitAModifier.get();

            _unit.setTitle(unit.getTitle());
            _unit.setDescription(unit.getDescription());

            return new ResponseEntity(unitService.save(_unit), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteUnit(@PathVariable("id") long id) {

        try {
            unitService.delete(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/adhere/{id}")
    public ResponseEntity<Adhere> updateUnit(@PathVariable Long id) {

        try {
            return new ResponseEntity<>(adhereService.save(id), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
