package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.PublicationMedia;

public interface PublicationMediaService {

    Object save(PublicationMedia publication);

    Object getById(Long idPublicationMedia);

}
