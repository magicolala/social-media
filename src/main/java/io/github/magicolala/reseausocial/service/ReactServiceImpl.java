package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.Publication;
import io.github.magicolala.reseausocial.entity.React;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.ReactRepository;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReactServiceImpl implements ReactService {

    private final ReactRepository    repository;
    private final PublicationService publicationService;
    private final UserUtil userUtil;

    @Override
    public React save(Long id) {
        Publication publication = (Publication) publicationService.getById(id);

        User user = userUtil.getCurrentUser();

        if (publication != null) {
            React react = new React();
            react.setPublication(publication);
            react.setUser(user);
            react.setLike(true);

            return repository.save(react);
        }

        return null;
    }

}
