package io.github.magicolala.reseausocial.service;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GenericServiceImpl implements GenericService {

    private final JpaRepository repository;

    public GenericServiceImpl(JpaRepository repository) {
        this.repository = repository;
    }

    @Override
    public List listAll() {
        return repository.findAll();
    }

    @Override
    public Object getById(Long id) {
        return repository.getById(id);
    }

    @Override
    public Object save(Object domainObject) {
        return repository.save(domainObject);
    }

    @Override
    public void delete(Long id) {
        repository.deleteById(id);
    }

}
