package io.github.magicolala.reseausocial.service;

import java.util.List;

public interface GenericService<T> {
    List<T> listAll();

    T getById(Long id);

    T save(T domainObject);

    void delete(Long id);
}
