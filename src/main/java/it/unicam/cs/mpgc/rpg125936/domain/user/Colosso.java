package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.repository.ItemRegistry;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("COLOSSO")
public class Colosso extends Enemy {

    public Colosso(String name) {
        super(name);
        this.setHealth(180); // Tanta vita
        
        // Il Colosso usa solo armi fisiche pesanti
        this.addItem(ItemRegistry.getRandomGun());
    }

    public Colosso(){}
}
