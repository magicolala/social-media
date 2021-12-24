package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.PublicationMedia;
import io.github.magicolala.reseausocial.repository.PublicationMediaRepository;
import io.github.magicolala.reseausocial.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationMediaServiceImpl implements PublicationMediaService {

    private final PublicationRepository      publicationRepository;
    private final PublicationMediaRepository publicationMediaRepository;

    @Override
    public Object save(PublicationMedia publicationMedia) {
        return publicationRepository.save(publicationMedia);
    }

    @Override
    public Object getById(Long idPublicationMedia) {
        return publicationMediaRepository.getById(idPublicationMedia);
    }

}
