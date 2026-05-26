package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;

public class WeaponOffer {
    private final long id;
    private final String name;
    private final double damage;
    private final double price;

    public WeaponOffer(long id, String name, double damage, double price) {
        this.id = id;
        this.name = name;
        this.damage = damage;
        this.price = price;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public double getDamage() { return damage; }
    public double getPrice() { return price; }

    public Gun createItem() {
        return new Gun(name, damage, price);
    }
}
