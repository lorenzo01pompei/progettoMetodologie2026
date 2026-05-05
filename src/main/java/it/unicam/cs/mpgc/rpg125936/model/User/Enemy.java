package it.unicam.cs.mpgc.rpg125936.model.User;

public class Enemy extends User {

    public Enemy(String name) {
        super(name);
    }

    @Override
    public Enemy copy() {
        Enemy clone = new Enemy(this.getName());
        clone.setHealth(this.getHealth());
        clone.setLives(this.getLives());
        for(it.unicam.cs.mpgc.rpg125936.model.Item.Item i : this.getInventory()) {
            clone.addItem(i.copy());
        }
        return clone;
    }
}
