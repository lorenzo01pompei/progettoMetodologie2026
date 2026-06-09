package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;

/**
 * Classe astratta che rappresenta un'offerta di acquisto nel negozio.
 */
public abstract class Offer {
    private final long id;
    private final String name;
    private final double price;

    public Offer(long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }

    /// per mostrare l'offerta al giocatore
    public abstract String getDescription();
    /// per istanziare l'item corrispondente
    public abstract Item createItem();
}
