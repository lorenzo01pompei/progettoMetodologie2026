package it.unicam.cs.mpgc.rpg125936.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Nemico di tipo Boss. Ha salute molto alta e viene equipaggiato
 * da WorldInitializer con 2 armi e un incantesimo.
 */
@Entity
@DiscriminatorValue("BOSS")
public class Boss extends Enemy {

    private static final int BOSS_HEALTH = 300;

    public Boss(String name) {
        super(name);
        this.setHealth(BOSS_HEALTH);
    }

    public Boss(){}
}
