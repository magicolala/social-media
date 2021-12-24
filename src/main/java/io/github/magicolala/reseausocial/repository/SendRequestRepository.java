package io.github.magicolala.reseausocial.repository;

import io.github.magicolala.reseausocial.entity.SendRequest;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SendRequestRepository extends JpaRepository<SendRequest, Long> {

}