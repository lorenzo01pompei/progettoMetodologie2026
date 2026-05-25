package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("GUN")
public class Gun extends AbstractItem implements FightItem {

    @Column(name= "name")
    private String name;
    @Column(name= "damage")
    private double damage;
    @Column(name= "price")
    private double price;

    public Gun(String name, double damage, double price) {
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public Gun(){}

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

    public String useInFight(User target, String attackerName){
        target.decreaseHealth(this.getDamage());
        return attackerName + " attacca con " + this.getName() + " infliggendo " + this.getDamage() + " danni!";
    }

    @Override
    public Item copy() {
        return new Gun(this.name, this.damage, this.price);
    }

}
