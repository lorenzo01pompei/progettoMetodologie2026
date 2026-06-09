package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;

/**
 * Interfaccia per gli item utilizzabili in combattimento (armi e potenziali altre classi).
 * Definisce il contratto per il metodo userInFight per gestire l'uso dell'arma in un combattimento.
 */
public interface FightItem extends Item {
    String useInFight(User target, String attackerName);
    double getDamage();

    default boolean reduceDamage(double amount) {
        return false;
    }
}