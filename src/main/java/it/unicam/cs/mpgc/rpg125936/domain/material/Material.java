package it.unicam.cs.mpgc.rpg125936.domain.material;

import it.unicam.cs.mpgc.rpg125936.domain.item.AbstractItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("MATERIAL")
public class Material extends AbstractItem {

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public Material() {}

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