package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.SendRequest;


public interface SendRequestService {

    SendRequest save(long idFriend) throws Exception;

    SendRequest accept(long id, String isAccept);

}
