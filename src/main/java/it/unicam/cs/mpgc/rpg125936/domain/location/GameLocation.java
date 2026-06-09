package it.unicam.cs.mpgc.rpg125936.domain.location;

/**
 * Interfaccia comune per tutte le location del gioco (Lobby, Miniera, Mondo).
 * Ogni location ha un nome e specifica se puo essere minata.
 */
public interface GameLocation {
    String getName();
    boolean canBeMined();
}