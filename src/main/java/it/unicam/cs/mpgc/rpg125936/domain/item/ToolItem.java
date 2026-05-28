package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.location.GameLocation;

public interface ToolItem extends Item {
    // Restituisce true se l'interazione con l'ambiente ha avuto successo
    boolean interact(GameLocation currentLocation);
    String getName();
    int getDurability();
}
