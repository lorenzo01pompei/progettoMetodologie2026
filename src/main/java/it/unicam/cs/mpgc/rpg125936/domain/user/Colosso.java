package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.repository.ItemRegistry;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Nemico di tipo Colosso. Ha salute alta.
 * Si equipaggia autonomamente con una singola arma pesante.
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
    }
}
