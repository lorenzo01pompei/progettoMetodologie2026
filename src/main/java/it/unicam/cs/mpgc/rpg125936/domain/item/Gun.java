package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;

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

    @Override
    public void applyDamageReduction(double amount){
        this.damage -= amount;
    }

    public void useInFight(User target){
        target.decreaseHealth(this.getDamage());
    }

    @Override
    public Item copy() {
        return new Gun(this.name, this.damage, this.price);
    }

}
