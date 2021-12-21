package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import java.util.Date;

@Entity
@Setter
@Getter
public class PublicationExam extends Publication {

    private String       link; // lien
    private Date         datePassage; // datePassage
    private CategoryExam categoryExam; // categorieEpreuve
    private String       classe; // classe

    public enum CategoryExam {
        TASK, // DEVOIR
        EVALUATION, // EVALUATION
        TP, // TP
    }

}
