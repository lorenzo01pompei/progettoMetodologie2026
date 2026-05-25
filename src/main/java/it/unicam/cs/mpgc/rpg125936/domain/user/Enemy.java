package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="enemies")
public class Enemy extends User {

    public Enemy(String name) {
        super(name);
    }

    public Enemy(){}

}
