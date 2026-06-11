package it.unicam.cs.mpgc.rpg125936.domain.user;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Nemico di tipo Mago. Ha salute bassa.
 * Ha due incantesimi e un'arma da fuoco.
 */
@Entity
@DiscriminatorValue("MAGO")
public class Mago extends Enemy {

    private static final int MAGO_HEALTH = 60;

    public Mago(String name) {
        super(name);
        this.setHealth(MAGO_HEALTH);
    }

    public Mago(){}

    @Override
    public void equipDefault() {
        addItem(ItemRegistry.getRandomSpell());
        addItem(ItemRegistry.getRandomSpell());
        addItem(ItemRegistry.getRandomGun());
    }
}
