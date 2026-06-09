package it.unicam.cs.mpgc.rpg125936.domain.inventory;

import it.unicam.cs.mpgc.rpg125936.domain.item.AbstractItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Embeddable;
import jakarta.persistence.FetchType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;

import java.util.ArrayList;
import java.util.List;

/**
 * Contiene la lista di item posseduti da un utente..
 * Fornisce operazioni di aggiunta, rimozione e recupero degli item, incapsulando
 * la gestione della collezione e separate dalla logica di gioco.
 */
@Embeddable
public class Inventory {

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, targetEntity = AbstractItem.class, fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private List<Item> items;

    public Inventory(){
        this.items= new ArrayList<>();
    }

    public void addItem(Item item){
        this.items.add(item);
    }

    public List<Item> getItems(){
        return this.items;
    }


}
