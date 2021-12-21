package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
public class SendRequest extends BasicEntity {

    private State state;

    @OneToMany(mappedBy = "sendRequest", orphanRemoval = true)
    private List<User> friends = new ArrayList<>();

    @OneToOne(orphanRemoval = true)
    @JoinColumn(name = "user_id")
    private User user;

    public enum State {
        WAITING, // EN ATTENTE
        ACCEPTED, // ACCEPTER
        REFUSED, // REFUSER
    }

}
