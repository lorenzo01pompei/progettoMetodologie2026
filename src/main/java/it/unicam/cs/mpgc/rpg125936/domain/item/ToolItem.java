package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.location.GameLocation;

/**
 * Interfaccia per gli item strumento, utilizzabili per interagire con l'ambiente.
 * Il metodo interact verifica se l'item puo essere
 * utilizzato nella posizione corrente e, in caso affermativo, ne consuma l'uso.
 */
public interface ToolItem extends Item {
    boolean interact(GameLocation currentLocation);
    int getDurability();
}
