package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

public class Lobby implements GameLocation {
    @Override
    public String getName() { return "Lobby Iniziale"; }

    @Override
    public void enter(Player player) {
        System.out.println("Benvenuto nella Lobby! Qui puoi potenziare il tuo equipaggiamento.");
    }

    @Override
    public boolean canBeMined() {
        return false;
    }

    @Override
    public void executeAction(Player player) {
        // Logica per lo shop: es. player.addMoney(-10) se compra qualcosa
        System.out.println("Accesso allo Shop... (Logica da implementare)");
    }
}