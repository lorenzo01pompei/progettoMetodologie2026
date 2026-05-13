package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;

import java.util.ArrayList;
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
        Player clone = new Player(this.getName(),this.getMoney());
        clone.setHealth(this.getHealth());
        clone.setLives(this.getLives());
        for(Item i : this.getInventory()) {
            clone.addItem(i.copy());
        }
        for(List<Material> list : this.getMaterials()) {
            List<Material> newList = new ArrayList<>();
            for(Material m : list) {
                newList.add((Material) m.copy());
            }
            clone.addMaterialList(newList);
        }
        return clone;
    }

}