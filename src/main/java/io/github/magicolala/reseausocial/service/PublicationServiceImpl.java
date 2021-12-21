package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.repository.PublicationRepository;
import org.springframework.stereotype.Service;

@Service
public class PublicationServiceImpl extends GenericServiceImpl implements PublicationService {

    public PublicationServiceImpl(PublicationRepository repository) {
        super(repository);
    }

}
