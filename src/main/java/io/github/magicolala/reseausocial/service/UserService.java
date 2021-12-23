package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.User;

import java.util.Optional;


public interface UserService {

    User save(User user);

    User getById(Long id);

    Optional<User> findById(Long id);

    User findByEmail(String email);

}
