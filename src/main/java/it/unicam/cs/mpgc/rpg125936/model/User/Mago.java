package it.unicam.cs.mpgc.rpg125936.model.User;

import it.unicam.cs.mpgc.rpg125936.model.Item.ItemRegistry;

public class Mago extends Enemy {

    public Mago(String name) {
        super(name);
        this.setHealth(60); // Poca vita
        
        // Il Mago usa quasi esclusivamente magie
        this.addItem(ItemRegistry.getRandomSpell());
        this.addItem(ItemRegistry.getRandomSpell());
    }
}
