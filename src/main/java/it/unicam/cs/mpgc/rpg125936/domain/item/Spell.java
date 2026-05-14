package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;

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

    @Override
    public void applyDamageReduction(double amount) {
    }

    public void useInFight(User target){
        for(Item i : target.getInventory()) {
            if(i instanceof FightItem fightItem) {
                fightItem.applyDamageReduction(this.damage);
            }
        }
    }

    @Override
    public Item copy() {
        return new Spell(this.name, this.damage);
    }
}
