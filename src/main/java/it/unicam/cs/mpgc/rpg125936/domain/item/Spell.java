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

    public String useInFight(User target, String attackerName){
        for(Item i : target.getInventory()) {
            if(i instanceof FightItem fightItem) {
                if(fightItem.getDamage()-this.damage<=0){
                    target.getInventory().remove(fightItem);
                    return attackerName + " ha distrutto l'arma " + fightItem.getName() + ", non puo piu essere utilizzata";
                }
                fightItem.applyDamageReduction(this.damage);
            }
        }
        return attackerName + " lancia " + this.getName() + ": il danno delle armi dell'avversario viene ridotto di " + this.damage + " punti!";
    }

    @Override
    public Item copy() {
        return new Spell(this.name, this.damage);
    }
}
