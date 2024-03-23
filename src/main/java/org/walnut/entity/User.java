package org.walnut.entity;

import io.quarkus.hibernate.orm.panache.PanacheEntity;
import jakarta.persistence.*;

@Entity
@Table(name = "user", schema = "user")
public class User {

    @Id
    @SequenceGenerator(
            name = "userSequence",
            sequenceName = "user_id_seq",
            allocationSize = 1,
            initialValue = 1000)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "userSequence")
    private Long id;
    private String name;

    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
