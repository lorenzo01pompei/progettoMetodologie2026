package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;

/**
 * Offerta per l'acquisto di un'arma. Mostra il danno nella descrizione
 * e crea l'istanza di Gun corrispondente.
 */
public class WeaponOffer extends Offer {
    private final double damage;

    public WeaponOffer(long id, String name, double damage, double price) {
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
        return new Gun(getName(), damage, getPrice());
    }
}
