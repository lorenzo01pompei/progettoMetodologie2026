package it.unicam.cs.mpgc.rpg125936.model.Item;

import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;
import it.unicam.cs.mpgc.rpg125936.model.User.User;

public class Gun implements FightItem {

    private String name;
    private double damage;
    private double price;

    public Gun(String name, double damage, double price) {
        this.name = name;
        this.damage = damage;
        this.price = price;
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void decreaseDamage(double malus){
        this.damage-=malus;
    }

    public void useInFight(User target){
        target.decreaseHealth(this.getDamage());
    }

    @Override
    public Item copy() {
        return new Gun(this.name, this.damage, this.price);
    }

}
