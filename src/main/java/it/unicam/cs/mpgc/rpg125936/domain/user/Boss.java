package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.repository.ItemRegistry;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Nemico di tipo Boss. Ha salute molto alta.
 * Si equipaggia autonomamente con 2 armi da fuoco e un incantesimo.
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

    @Override
    public void equipDefault() {
        addItem(ItemRegistry.getRandomGun());
        addItem(ItemRegistry.getRandomGun());
        addItem(ItemRegistry.getRandomSpell());
    }
}
