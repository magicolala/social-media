package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;

@Entity
@Getter
@Setter
public class PublicationMedia extends Publication {

    private ReferenceMedia referenceMedia; // referenceMedia

    public enum ReferenceMedia {
        DOCUMENT, VIDEO, IMAGE
    }

}
