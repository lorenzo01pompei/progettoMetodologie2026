package it.unicam.cs.mpgc.rpg125936.domain.shop;

import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;

public class ToolOffer {
    private final long id;
    private final String name;
    private final int maxUses;
    private final double price;

    public ToolOffer(long id, String name, int maxUses, double price) {
        this.id = id;
        this.name = name;
        this.maxUses = maxUses;
        this.price = price;
    }

    public long getId() { return id; }
    public String getName() { return name; }
    public int getMaxUses() { return maxUses; }
    public double getPrice() { return price; }

    public Pickaxe createItem() {
        Pickaxe p = new Pickaxe();
        p.setDurability(maxUses);
        return p;
    }
}
