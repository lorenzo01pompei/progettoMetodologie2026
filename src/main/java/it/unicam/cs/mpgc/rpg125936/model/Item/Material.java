package it.unicam.cs.mpgc.rpg125936.model.Item;

public class Material implements Item {
    private String name;
    private double price;

    public Material(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public Item copy() {
        return new Material(this.name, this.price);
    }
}
