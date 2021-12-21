package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class Comment extends BasicEntity {

    private String text; // texte

    @OneToMany(mappedBy = "comment", orphanRemoval = true)
    private List<User> users = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

}
