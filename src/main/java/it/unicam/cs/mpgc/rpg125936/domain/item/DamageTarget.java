package it.unicam.cs.mpgc.rpg125936.domain.item;

import java.util.List;

public interface DamageTarget {
    void decreaseHealth(double damage);
    List<Item> getInventory();
    void addItem(Item item);
}
