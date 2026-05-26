package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;

public class SpellOffer {
    private final long id;
    private final String name;
    private final double damage;
    private final double price;

    public SpellOffer(long id, String name, double damage, double price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public double getPrice() { return price; }
    public double getDamage() { return damage; }

    public Spell createSpell() {
        return new Spell(name, damage, price);
    }
}
