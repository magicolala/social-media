package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Adhere extends BasicEntity {

    @OneToMany(mappedBy = "adhere", orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @OneToMany(mappedBy = "adhere", orphanRemoval = true)
    private List<Unit> units = new ArrayList<>();

}
