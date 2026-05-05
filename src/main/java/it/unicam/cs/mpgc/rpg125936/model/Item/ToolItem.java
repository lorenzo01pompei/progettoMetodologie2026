package it.unicam.cs.mpgc.rpg125936.model.Item;

import it.unicam.cs.mpgc.rpg125936.ambiente.GameLocation;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;

public interface ToolItem extends Item {
    // Restituisce true se l'interazione con l'ambiente ha avuto successo
    boolean interact(GameLocation currentLocation);
}
