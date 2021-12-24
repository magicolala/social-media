package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.UserRepository;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository repository;

    @Override
    public User save(User user) {
        return repository.save(user);
    }

    @Override
    public User getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Optional<User> findById(Long id) {
        return repository.findById(id);
    }

    @Override
    public User findByEmail(String email) {
        return repository.findByEmail(email);
    }

    @Override
    public User upload(User user, User currentUser) {
        currentUser.setFirstname(user.getFirstname());
        currentUser.setLastname(user.getLastname());
        currentUser.setDescription(user.getDescription());
        currentUser.setGender(user.getGender());
        currentUser.setPhone(user.getPhone());

        return repository.save(currentUser);
    }


}
