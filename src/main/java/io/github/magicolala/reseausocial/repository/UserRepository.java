package io.github.magicolala.reseausocial.repository;

import io.github.magicolala.reseausocial.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

    User findByEmail(String email);

}
