package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.repository.UnitRepository;
import org.springframework.stereotype.Service;

@Service
public class UnitServiceImpl extends GenericServiceImpl implements UnitService{

    public UnitServiceImpl(UnitRepository repository) {
        super(repository);
    }

}
