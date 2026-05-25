package it.unicam.cs.mpgc.rpg125936.domain.inventory;

import it.unicam.cs.mpgc.rpg125936.domain.item.AbstractItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

@Embeddable
public class Inventory {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = AbstractItem.class)
    @JoinColumn(name = "user_id")
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
