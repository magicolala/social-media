package io.github.magicolala.reseausocial.repository;

import io.github.magicolala.reseausocial.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {

}