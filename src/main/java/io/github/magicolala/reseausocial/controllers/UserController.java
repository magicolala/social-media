package io.github.magicolala.reseausocial.controllers;

import io.github.magicolala.reseausocial.entity.Publication;
import io.github.magicolala.reseausocial.entity.SendMessage;
import io.github.magicolala.reseausocial.entity.SendRequest;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.service.SendMessageService;
import io.github.magicolala.reseausocial.service.SendRequestService;
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

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class UserController {

    private final UserService        userService;
    private final UserUtil           userUtil;
    private final SendRequestService sendRequestService;
    private final SendMessageService sendMessageService;

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
    public ResponseEntity<SendRequest> sendRequest(@PathVariable("id") long id) {

        try {
            SendRequest sendRequest = sendRequestService.save(id);

            return new ResponseEntity<>(sendRequest, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping(value = "/accept-friend/{idRequest}/{isAccept}")
    public ResponseEntity<SendRequest> acceptFriend(@PathVariable("idRequest") long id, @PathVariable("isAccept") String isAccept) {

        try {
            SendRequest sendRequest = sendRequestService.accept(id, isAccept);

            return new ResponseEntity<>(sendRequest, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PostMapping(value = "/send-message/{idUser}")
    public ResponseEntity<SendMessage> sendMessage(@RequestBody SendMessage message, @PathVariable("idUser") long idUser) {

        try {
            SendMessage sendMessage = sendMessageService.save(message, idUser);

            return new ResponseEntity<>(sendMessage, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();

            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }


    @PutMapping(value = "/update-profil")
    public ResponseEntity<User> sendMessage(@RequestBody User user) {

        try {
            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

}
