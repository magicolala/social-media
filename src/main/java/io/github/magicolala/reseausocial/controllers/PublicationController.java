package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.*;
import io.github.magicolala.reseausocial.service.*;
import io.github.magicolala.reseausocial.utils.FileUploadUtil;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/publication")
@RequiredArgsConstructor
public class PublicationController {

    @Qualifier(value = "Publication")
    private final PublicationService      publicationService;
    private final PublicationExamService  publicationExamService;
    private final PublicationMediaService publicationMediaService;
    private final ReactService            reactService;
    private final CommentService          commentService;
    private final UserUtil                userUtil;

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

    @PostMapping("/exam")
    public ResponseEntity<PublicationExam> savePublicationExam(@RequestBody PublicationExam publication) {

        try {
            PublicationExam _publication = (PublicationExam) publicationExamService.save(publication);

            return new ResponseEntity<>(_publication, HttpStatus.CREATED);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PostMapping("/media")
    public ResponseEntity<PublicationMedia> savePublicationMedia(@RequestBody PublicationMedia publication) {

        try {
            PublicationMedia _publication = (PublicationMedia) publicationMediaService.save(publication);

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
    public ResponseEntity<Publication> updatePublication(@RequestBody PublicationExam publication, @PathVariable Long id) {

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

    @PostMapping(value = "/upload-media/{idPublicationMedia}")
    public ResponseEntity<User> uploadFile(@RequestParam("file") MultipartFile multipartFile, @PathVariable Long idPublicationMedia) {

        try {
            PublicationMedia publicationMedia = (PublicationMedia) publicationMediaService.getById(idPublicationMedia);

            if (publicationMedia != null) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                publicationMedia.setFileName(fileName);
                PublicationMedia _publicationMedia = (PublicationMedia) publicationService.save(publicationMedia);

                String uploadDir = "src/main/resources/static/publication-media/" + _publicationMedia.getId();

                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
