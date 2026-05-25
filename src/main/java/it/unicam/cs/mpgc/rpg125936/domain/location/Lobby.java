package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

public class Lobby implements GameLocation {

    private final Shop shop;

    public Lobby() {
        this.shop = new Shop();
    }

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

    public Shop getShop() {
        return shop;
    }
}