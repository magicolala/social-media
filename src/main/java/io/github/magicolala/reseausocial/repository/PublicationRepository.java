package io.github.magicolala.reseausocial.repository;

import io.github.magicolala.reseausocial.entity.Publication;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;

@Primary
@Component("Publication")
public interface PublicationRepository extends JpaRepository<Publication, Long> {

}
