package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.Comment;

public interface CommentService {
    Comment save(Comment comment, Long idPublication);
}
