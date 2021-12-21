package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class React extends BasicEntity {

    private boolean isLike = false;

    @OneToMany(mappedBy = "react", orphanRemoval = true)
    private List<User> users = new ArrayList<>();

}
