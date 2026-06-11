package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.shop.Shop;

/**
 * Location iniziale del gioco. Contiene il negozio dove il giocatore
 * puo acquistare e vendere oggetti.
 */
public class Lobby implements GameLocation {

    private final Shop shop;

    public Lobby(Shop shop) {
        this.shop = shop;
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