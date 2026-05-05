package it.unicam.cs.mpgc.rpg125936.model.User;

import it.unicam.cs.mpgc.rpg125936.model.Item.ItemRegistry;

public class Colosso extends Enemy {

    public Colosso(String name) {
        super(name);
        this.setHealth(180); // Tanta vita
        
        // Il Colosso usa solo armi fisiche pesanti
        this.addItem(ItemRegistry.getRandomGun());
    }
}
