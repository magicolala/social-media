package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.SendMessage;

public interface SendMessageService {

    SendMessage save(SendMessage message, long idUser);

    SendMessage read(long idMessage);

}
