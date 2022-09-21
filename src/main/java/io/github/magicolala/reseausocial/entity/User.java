package io.github.magicolala.reseausocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.*;

import static io.github.magicolala.reseausocial.entity.SendRequest.State.ACCEPTED;

@Entity
//@Getter
@Setter
public class User extends BasicEntity implements UserDetails {

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
    private Role    role = Role.USER; // rôle

    @OneToMany(mappedBy = "user", orphanRemoval = true)
    private List<SendRequest> sendRequests = new ArrayList<>();

    @OneToMany(mappedBy = "friend", orphanRemoval = true)
    private List<SendRequest> receiveRequests = new ArrayList<>();

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

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        GrantedAuthority grantedAuthority = new SimpleGrantedAuthority("ROLE_" + this.getRole().toString());

        return Collections.singletonList(grantedAuthority);
    }

    @JsonIgnore
    @JsonProperty(value = "password")
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return this.email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.isActive();
    }

    public List<User> getFriends() {
        List<SendRequest> sendRequests = this.getSendRequests();
        List<User> friends = new ArrayList<>();

        sendRequests.forEach(sendRequest -> {
            if (Objects.equals(sendRequest.getState().toString(), ACCEPTED.toString())) {
                friends.add(sendRequest.getFriend());
            }
        });

        return friends;
    }

    public String getLastname() {
        return lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public Gender getGender() {
        return gender;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone() {
        return phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getDescription() {
        return description;
    }

    public boolean isActive() {
        return true;
    }

    public Role getRole() {
        return role;
    }

    public List<SendRequest> getSendRequests() {
        return sendRequests;
    }

    public List<SendRequest> getReceiveRequests() {
        return receiveRequests;
    }

    public List<SendMessage> getSendMessages() {
        return sendMessages;
    }

    public List<SendMessage> getReceiveMessages() {
        return receiveMessages;
    }

    public List<Publication> getPublications() {
        return publications;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public List<React> getReacts() {
        return reacts;
    }

    public List<Adhere> getAdheres() {
        return adheres;
    }

}


