package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Publication extends BasicEntity {

    @Enumerated(EnumType.STRING)
    private Visibility visibility; // visibilité
    private String title; // titre
    private String text; // texte

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;


    @OneToMany(mappedBy = "publication", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    public enum Visibility {
        PUBLIC, // PUBLIC
        PRIVATE // PRIVE
    }

}
