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
    private String  avatar; // avatar
    private String  description; // description
    @Column(nullable = false)
    private boolean isActive; // actif ?
    @Enumerated(EnumType.STRING)
    private Role role; // rôle

    @OneToMany(mappedBy = "transmitter", orphanRemoval = true)
    private List<SendMessage> sendMessages = new ArrayList<>();

    @OneToMany(mappedBy = "recipient", orphanRemoval = true)
    private List<SendMessage> receiveMessages = new ArrayList<>();

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<Publication> publications = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "react_id")
    private React react;

    @ManyToOne
    @JoinColumn(name = "comment_id")
    private Comment comment;

    @ManyToMany(mappedBy = "admins")
    private List<Unit> units = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name = "adhere_id")
    private Adhere adhere;

    @ManyToOne
    @JoinColumn(name = "send_request_id")
    private SendRequest sendRequest;

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


