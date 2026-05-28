package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;

public class Lobby implements GameLocation {

    private final Shop shop;

    public Lobby() {
        this.shop = new Shop();
    }

    @Override
    public String getName() { return "Lobby Iniziale"; }

    @Override
    public boolean canBeMined() {
        return false;
    }

    public Shop getShop() {
        return shop;
    }
}