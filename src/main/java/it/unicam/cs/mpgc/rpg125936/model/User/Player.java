package it.unicam.cs.mpgc.rpg125936.model.User;

import it.unicam.cs.mpgc.rpg125936.model.Item.Item;
import it.unicam.cs.mpgc.rpg125936.model.Item.Pickaxe;

import java.util.List;

public class Player extends User{
    private int money;
    // private int pickaxeDurability;
    // Lista di oggetti (armi, materiali, etc.)

    public Player(String name, int initialMoney) {
        super(name);
        this.money = initialMoney;
    }

    // Metodi per gestire la salute, i soldi e lo scavo
    public void addMoney(int amount) { this.money += amount; }
    //public void reducePickaxe() { this.pickaxeDurability--; }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }



    @Override
    public Player copy() {
        Player clone = new Player(this.getMoney());
        clone.setHealth(this.getHealth());
        clone.setLives(this.getLives());
        for(Item i : this.getInventory()) {
            clone.addItem(i.copy());
        }
        return clone;
    }

}