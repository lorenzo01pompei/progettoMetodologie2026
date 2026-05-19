package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;

public interface FightItem extends Item {
    String useInFight(User target, String attackerName);
    double getDamage();
    String getName();
    void applyDamageReduction(double amount);
}