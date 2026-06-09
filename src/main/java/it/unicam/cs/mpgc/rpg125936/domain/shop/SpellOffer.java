package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;

/**
 * Offerta per l'acquisto di un incantesimo (Spell). Mostra il danno nella
 * descrizione e crea l'istanza di Spell corrispondente
 */
public class SpellOffer extends Offer {
    private final double damage;

    public SpellOffer(long id, String name, double damage, double price) {
        super(id, name, price);
        this.damage = damage;
    }

    public double getDamage() { return damage; }

    @Override
    public String getDescription() {
        return getName() + "  \u2022  Danno: " + damage;
    }

    @Override
    public Item createItem() {
        return new Spell(getName(), damage, getPrice());
    }
}
