package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.PublicationExam;
import io.github.magicolala.reseausocial.repository.PublicationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PublicationExamServiceImpl implements PublicationExamService {

    private final PublicationRepository publicationRepository;

    @Override
    public Object save(PublicationExam publicationExam) {
        return publicationRepository.save(publicationExam);
    }

}
