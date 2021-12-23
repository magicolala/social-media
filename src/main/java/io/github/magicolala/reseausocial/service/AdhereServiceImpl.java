package io.github.magicolala.reseausocial.service;

import io.github.magicolala.reseausocial.entity.Adhere;
import io.github.magicolala.reseausocial.entity.Unit;
import io.github.magicolala.reseausocial.entity.User;
import io.github.magicolala.reseausocial.repository.AdhereRepository;
import io.github.magicolala.reseausocial.repository.UnitRepository;
import io.github.magicolala.reseausocial.utils.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AdhereServiceImpl implements AdhereService {

    private final AdhereRepository adhereRepository;
    private final UserUtil       userUtil;
    private final UnitRepository unitRepository;

    @Override
    public Adhere save(Long idUnit) {
        Optional<Unit> unit = unitRepository.findById(idUnit);

        if (unit.isPresent()) {
            Unit _unit = unit.get();
            User           user = userUtil.getCurrentUser();
            Adhere adhere = new Adhere();
            adhere.setUser(user);
            adhere.setUnit(_unit);

            return adhereRepository.save(adhere);
        }

        return null;
    }

}
