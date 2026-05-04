package it.unicam.cs.mpgc.rpg125936.model.User;

import it.unicam.cs.mpgc.rpg125936.model.Item.Item;

import java.util.ArrayList;
import java.util.List;

public class User {

    private double health;
    private int lives;
    // Lista di oggetti (armi, materiali, etc.)
    private List<Item> inventory;

    public User() {
        this.health = 100;
        this.lives = 3;
        this.inventory = new ArrayList<>();
    }

    //public void reducePickaxe() { this.pickaxeDurability--; }

    public double getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public List<Item> getInventory() {
        return inventory;
    }

    public void setInventory(List<Item> inventory) {
        this.inventory = inventory;
    }

    public void addItem(Item item){
        this.inventory.add(item);
    }

    public void decreaseHealth(double danno){
        this.health-=danno;
    }

}