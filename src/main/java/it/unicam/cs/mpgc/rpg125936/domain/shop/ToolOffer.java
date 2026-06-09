package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;

/**
 * Offerta per l'acquisto di uno strumento. Mostra il numero di usi
 * nella descrizione e crea l'istanza di Pickaxe corrispondente.
 */
public class ToolOffer extends Offer {
    private final int maxUses;

    public ToolOffer(long id, String name, int maxUses, double price) {
        super(id, name, price);
        this.maxUses = maxUses;
    }

    public int getMaxUses() { return maxUses; }

    @Override
    public String getDescription() {
        return getName() + "  \u2022  Usi: " + maxUses;
    }

    @Override
    public Item createItem() {
        return new Pickaxe(getName(), maxUses, getPrice());
    }
}
