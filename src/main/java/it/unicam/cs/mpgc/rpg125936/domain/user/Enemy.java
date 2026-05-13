package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;

public class Enemy extends User {

    public Enemy(String name) {
        super(name);
    }

    @Override
    public Enemy copy() {
        Enemy clone = new Enemy(this.getName());
        clone.setHealth(this.getHealth());
        clone.setLives(this.getLives());
        for(Item i : this.getInventory()) {
            clone.addItem(i.copy());
        }
        return clone;
    }
}
