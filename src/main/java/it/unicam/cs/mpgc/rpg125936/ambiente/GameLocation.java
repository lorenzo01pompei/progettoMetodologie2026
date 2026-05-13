package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

public interface GameLocation {
    String getName();
    void enter(Player player);
    void executeAction(Player player);
    boolean canBeMined();
}