package io.github.magicolala.reseausocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class User extends BasicEntity {

    private String  password; // password
    private String  lastname; // nom
    private String  firstname; // prenom
    @Enumerated(EnumType.STRING)
    private Gender  gender; // sexe
    @NotNull
    @Column(nullable = false, unique = true)
    private String  email; // email
    private String  phone; // telephone
    @Column(length = 64)
    private String  avatar; // avatar
    private String  description; // description
    @Column(nullable = false)
    private boolean isActive = false; // actif ?
    @Enumerated(EnumType.STRING)
    private Role    role; // rôle

    @OneToMany(mappedBy = "transmitter", orphanRemoval = true)
    private List<SendMessage> sendMessages = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", orphanRemoval = true)
    private List<SendMessage> receiveMessages = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Publication> publications = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Comment> comments = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<React> reacts = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Adhere> adheres = new ArrayList<>();

    public enum Gender {
        MALE, // MASCULIN
        FEMALE // FEMININ
    }

    public enum Role {
        ADMIN,
        USER
    }

    @JsonIgnore
    @JsonProperty(value = "password")
    public String getPassword() {
        return password;
    }

}


