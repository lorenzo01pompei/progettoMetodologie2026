package it.unicam.cs.mpgc.rpg125936.model.Item;

import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;
import it.unicam.cs.mpgc.rpg125936.model.User.User;

public interface FightItem extends Item {
    // Ha bisogno del giocatore e del nemico per funzionare
    void useInFight(User target);
    double getDamage();
    String getName();
}