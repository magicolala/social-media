package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.Comment;
import io.github.magicolala.reseausocial.entity.Publication;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.CommentRepository;
import io.github.magicolala.reseausocial.repository.PublicationRepository;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class CommentServiceImpl implements CommentService {

    private final PublicationRepository publicationRepository;
    private final UserUtil              userUtil;
    private final CommentRepository     commentRepository;

    @Override
    public Comment save(Comment comment, Long idPublication) {
        Optional<Publication> _publication = publicationRepository.findById(idPublication);

        if (_publication.isPresent()) {
            Publication publication = _publication.get();
            User        user        = userUtil.getCurrentUser();
            comment.setPublication(publication);
            comment.setUser(user);

            return commentRepository.save(comment);
        }

        return null;
    }

}
