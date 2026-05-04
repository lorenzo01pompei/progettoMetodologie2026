package it.unicam.cs.mpgc.rpg125936.model.Item;

import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;
import it.unicam.cs.mpgc.rpg125936.model.User.User;

public class Spell implements FightItem {

    private String name;
    private double damage;

    public Spell(String name, double damage) {
        this.name = name;
        this.damage = damage;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }

    public void useInFight(User target){
        for(Item i : target.getInventory()) {
            if(i instanceof Gun) {
                Gun arma = (Gun)i;
                arma.decreaseDamage(this.damage);
            }
        }
    }
}
