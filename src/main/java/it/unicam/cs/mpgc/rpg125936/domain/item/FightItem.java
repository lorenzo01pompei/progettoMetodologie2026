package it.unicam.cs.mpgc.rpg125936.domain.item;

public interface FightItem extends Item {
    void useInFight(DamageTarget target);
    double getDamage();
    String getName();
    void applyDamageReduction(double amount);
}