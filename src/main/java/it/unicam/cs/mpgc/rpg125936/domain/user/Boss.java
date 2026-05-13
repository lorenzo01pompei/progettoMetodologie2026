package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.repository.ItemRegistry;

public class Boss extends Enemy {

    public Boss(String name) {
        super(name);
        this.setHealth(300); // Vita altissima
        
        // Il Boss ha un arsenale completo
        this.addItem(ItemRegistry.getRandomGun());
        this.addItem(ItemRegistry.getRandomGun());
        this.addItem(ItemRegistry.getRandomSpell());
    }
}
