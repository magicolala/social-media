package io.github.magicolala.reseausocial.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@MappedSuperclass
@Getter
@Setter
public abstract class BasicEntity implements Serializable {

    @Id
    @Column(name = "id", nullable = false)
    @GeneratedValue(strategy=GenerationType.TABLE)
    private Long id;

    @CreationTimestamp
    @Column(updatable = false)
    private Date dateCreated; // date de création
    @UpdateTimestamp
    private Date dateModified; // date de modification

}
