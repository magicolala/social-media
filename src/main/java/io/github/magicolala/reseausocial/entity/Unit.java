package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Unit extends BasicEntity {

    private String title; // libelle
    private String description; // description

    @ManyToMany
    @JoinTable(name = "unit_admins",
            joinColumns = @JoinColumn(name = "unit_id"),
            inverseJoinColumns = @JoinColumn(name = "admin_id"))
    private List<User> admins = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "adhere_id")
    private Adhere adhere;

}
