package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.service.UserService;
import io.github.magicolala.reseausocial.utils.FileUploadUtil;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final UserUtil    userUtil;

    @PostMapping(value = "/upload-avatar")
    public ResponseEntity<User> uploadFile(@RequestParam("avatar") MultipartFile multipartFile) {

        try {
            User user = userUtil.getCurrentUser();

            if (user != null) {
                String fileName = StringUtils.cleanPath(Objects.requireNonNull(multipartFile.getOriginalFilename()));
                user.setAvatar(fileName);
                User _user = userService.save(user);

                String uploadDir = "src/main/resources/static/users-avatar/" + _user.getId();

                FileUploadUtil.saveFile(uploadDir, fileName, multipartFile);

                return new ResponseEntity<>(null, HttpStatus.CREATED);
            }

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);

        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping(value = "/send-request/{id}")
    public ResponseEntity<User> sendRequest(@PathVariable("id") long id) {

        try {
            User emitter = userUtil.getCurrentUser();

            if (emitter != null) {
                Optional<User> receiver = userService.findById(id);

                if (receiver.isPresent()) {

                }
            }

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
