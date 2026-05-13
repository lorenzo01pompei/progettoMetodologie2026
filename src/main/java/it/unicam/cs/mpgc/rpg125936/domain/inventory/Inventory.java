package it.unicam.cs.mpgc.rpg125936.domain.inventory;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;

import java.util.ArrayList;
import java.util.List;

public class Inventory {
    private List<Item> items;

    public Inventory(){
        this.items= new ArrayList<>();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public void removeItem(Item item){
        this.items.remove(item);
    }

    public List<Item> getItems(){
        return this.items;
    }


}
