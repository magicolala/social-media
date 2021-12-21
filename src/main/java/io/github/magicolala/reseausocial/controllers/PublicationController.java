package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.Publication;
import io.github.magicolala.reseausocial.service.PublicationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/publication")
@RequiredArgsConstructor
public class PublicationController {
    @Qualifier(value = "Publication")
    private final PublicationService publicationService;

    @GetMapping("/{id}")
    public ResponseEntity<Publication> getPublicationById(@PathVariable(value = "id") Long id) {

        Optional<Publication> publication = (Optional<Publication>) publicationService.getById(id);

        return publication.map(value -> new ResponseEntity<>(value, HttpStatus.OK)).orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));

    }

    @PostMapping()
    public ResponseEntity<Publication> savePublication(@RequestBody Publication publication) {

        try {
            Publication _publication = (Publication) publicationService.save(publication);

            return new ResponseEntity<>(_publication, HttpStatus.CREATED);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Publication>> listPublication() {

        try {
            List<Publication> publications;
            publications = publicationService.listAll();

            if (publications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(publications, HttpStatus.OK);

        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/{id}")
    public ResponseEntity<Publication> updatePublication(@RequestBody Publication publication, @PathVariable Long id) {

        Optional<Publication> publicationAModifier = (Optional<Publication>) publicationService.getById(id);

        if (publicationAModifier.isPresent()) {
            Publication _publication = publicationAModifier.get();

            _publication.setTitle(publication.getTitle());
            _publication.setVisibility(publication.getVisibility());
            _publication.setText(publication.getText());

            return new ResponseEntity(publicationService.save(_publication), HttpStatus.OK);

        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deletePublication(@PathVariable("id") long id) {

        try {
            publicationService.delete(id);

            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }
}
