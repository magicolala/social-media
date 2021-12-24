package io.github.magicolala.reseausocial.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
public class SendRequest extends BasicEntity {

    @Enumerated(EnumType.STRING)
    private State state;

    @ManyToOne
    @JoinColumn(name = "friend_id")
    @JsonIgnore
    private User friend;

    @ManyToOne
    @JoinColumn(name = "user_id")
    @JsonIgnore
    private User user;

    public enum State {
        WAITING, // EN ATTENTE
        ACCEPTED, // ACCEPTER
        REFUSED, // REFUSER
    }

}
