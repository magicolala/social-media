package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

}
