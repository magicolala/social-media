package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;

@Getter
@Setter
@Entity
public class SendMessage extends BasicEntity {

    private String  content; // contenu
    private Date    dateSended; // date d'envoie
    private boolean isRead; // lu

    @ManyToOne
    @JoinColumn(name = "transmitter_ID")
    private User transmitter; // émetteur

    @ManyToOne
    @JoinColumn(name = "recipient_ID")
    private User recipient; // destinataire

}
