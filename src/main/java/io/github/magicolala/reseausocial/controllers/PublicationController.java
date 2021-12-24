package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.Comment;
import io.github.magicolala.reseausocial.entity.Publication;
import io.github.magicolala.reseausocial.entity.React;
import io.github.magicolala.reseausocial.service.CommentService;
import io.github.magicolala.reseausocial.service.PublicationService;
import io.github.magicolala.reseausocial.service.ReactService;
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
    private final ReactService   reactService;
    private final CommentService commentService;

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
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping()
    public ResponseEntity<List<Publication>> listPublication() {

        try {
            List<Publication> publications;
            publications = publicationService.listAll(); // TODO Pagination

            if (publications.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(publications, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

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
            e.printStackTrace();

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/like/{id}")
    public ResponseEntity<React> likePublication(@PathVariable Long id) {
        React react = reactService.save(id);

        if (react != null) {
            return new ResponseEntity(react, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/comment/{id}")
    public ResponseEntity<Comment> commentPublication(@RequestBody Comment comment, @PathVariable Long id) {

        if (commentService.save(comment, id) != null) {
            return new ResponseEntity<>(comment, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }

}
