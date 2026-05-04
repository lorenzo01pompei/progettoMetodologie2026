package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.model.User.Player;

public interface GameLocation {
    String getName();
    void enter(Player player);
    // Metodo per gestire l'azione principale del luogo
    void executeAction(Player player);
}