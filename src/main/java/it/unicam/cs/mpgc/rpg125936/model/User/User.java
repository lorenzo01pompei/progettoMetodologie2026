package it.unicam.cs.mpgc.rpg125936.model.User;

import it.unicam.cs.mpgc.rpg125936.model.Item.Item;
import it.unicam.cs.mpgc.rpg125936.model.Item.Material;

import java.util.ArrayList;
import java.util.List;

public class User {

    private String name;
    private double health;
    private int lives;
    // Lista di oggetti (armi, picconi, etc.)
    private List<Item> inventory;
    
    // Lista di liste di materiali (es. una lista per bronzo, una per argento, una per oro)
    private List<List<Material>> materials;

    public User(String name) {
        this.name = name;
        this.health = 100;
        this.lives = 3;
        this.inventory = new ArrayList<>();
        this.materials = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
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

    public List<List<Material>> getMaterials() {
        return materials;
    }

    public void setMaterials(List<List<Material>> materials) {
        this.materials = materials;
    }

    public void addMaterialList(List<Material> materialList) {
        this.materials.add(materialList);
    }

    public void addMaterial(Material material) {
        for (List<Material> list : materials) {
            if (!list.isEmpty() && list.get(0).getName().equals(material.getName())) {
                list.add(material);
                return;
            }
        }
        List<Material> newList = new ArrayList<>();
        newList.add(material);
        this.materials.add(newList);
    }

    public void decreaseHealth(double danno){
        this.health= this.health-danno;
    }

    public User copy() {
        User clone = new User(this.name);
        clone.setHealth(this.getHealth());
        clone.setLives(this.getLives());
        for(Item i : this.inventory) {
            clone.addItem(i.copy());
        }
        // Copia dei materiali
        for(List<Material> list : this.materials) {
            List<Material> newList = new ArrayList<>();
            for(Material m : list) {
                newList.add((Material) m.copy());
            }
            clone.addMaterialList(newList);
        }
        return clone;
    }

}