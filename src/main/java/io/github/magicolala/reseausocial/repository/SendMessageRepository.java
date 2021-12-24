package io.github.magicolala.reseausocial.repository;

import io.github.magicolala.reseausocial.entity.SendMessage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendMessageRepository extends JpaRepository<SendMessage, Long> {

}