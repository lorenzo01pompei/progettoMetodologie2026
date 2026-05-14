package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;

import java.util.ArrayList;
import java.util.List;

public class Player extends User{
    private double money;
    // private int pickaxeDurability;
    // Lista di oggetti (armi, materiali, etc.)

    public Player(String name, double initialMoney) {
        super(name);
        this.money = initialMoney;
    }

    public void addMoney(double amount) { this.money += amount; }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
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