package it.unicam.cs.mpgc.rpg125936.domain.item;

import jakarta.persistence.*;

/**
 * Classe astratta che centralizza i campi comuni (id, name, price) per tutti i tipi di item,
 * Mappa la tabella JPA "items" con discriminante "item_type" per distinguere Gun, Spell, Pickaxe, Material, ecc.
 */
@Entity
@Table(name = "items")

@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "item_type", discriminatorType = DiscriminatorType.STRING)
public abstract class AbstractItem implements Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private double price;

    public AbstractItem() {}

    public AbstractItem(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }
}