package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.health.Health;
import it.unicam.cs.mpgc.rpg125936.domain.inventory.Inventory;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.material.MaterialStorage;
import jakarta.persistence.*;


import java.util.List;
import java.util.Map;

/**
 * Entita base per tutti gli utenti del gioco (giocatori e nemici).
 * Contiene i componenti Health, Inventory e MaterialStorage,
 * delegando a ciascuno la gestione specifica.
 */
@Entity
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "user_type", discriminatorType = DiscriminatorType.STRING)
@Table(name="Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name= "name", nullable = false)
    private String name;

    @Embedded
    private Health healthStatus;
    @Embedded
    private Inventory inventory;
    @Embedded
    private MaterialStorage materialStorage;

    public User(String name) {
        this.name = name;
        this.healthStatus = new Health(100, 3);
        this.inventory = new Inventory();
        this.materialStorage = new MaterialStorage();
    }
    public User(){}

    public Long getId(){ return id;}
    public void setId(Long id) {this.id=id;}
    public String getName() {
        return name;
    }

    public Health getHealthStatus() {
        return healthStatus;
    }

    public void setHealth(int health) {
        healthStatus.setHealth(health);
    }

    public int getLives() {
        return healthStatus.getLives();
    }

    public void setLives(int lives) {
        healthStatus.setLives(lives);
    }

    public List<Item> getInventory() {
        return inventory.getItems();
    }

    public void addItem(Item item){
        this.inventory.addItem(item);
    }

    public Map<String, List<Material>> getMaterials() {
        return materialStorage.getMaterials();
    }

    public void addMaterial(Material material) {
        this.materialStorage.addMaterial(material);
    }

    public void removeMaterials() {
         materialStorage.removeAll();
    }

    public void decreaseHealth(int damage){
        this.healthStatus.decreaseHealth(damage);
    }

}