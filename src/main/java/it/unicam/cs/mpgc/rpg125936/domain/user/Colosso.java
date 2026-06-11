package it.unicam.cs.mpgc.rpg125936.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Nemico di tipo Colosso. Ha salute alta.
 * ha 2 armi da fuoco.
 */
@Entity
@DiscriminatorValue("COLOSSO")
public class Colosso extends Enemy {

    private static final int COLOSSO_HEALTH = 180;

    public Colosso(String name) {
        super(name);
        this.setHealth(COLOSSO_HEALTH);
    }

    public Colosso(){}

    @Override
    public void equipDefault() {
        addItem(ItemRegistry.getRandomGun());
        addItem(ItemRegistry.getRandomGun());
    }
}
