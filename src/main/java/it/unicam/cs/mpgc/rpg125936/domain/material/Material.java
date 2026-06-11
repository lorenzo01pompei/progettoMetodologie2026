package it.unicam.cs.mpgc.rpg125936.domain.material;

import it.unicam.cs.mpgc.rpg125936.domain.item.AbstractItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

/**
 * Materiale(lingotti di rame, argento, oro) ottenibile
 * estraendo nelle miniere. Puo essere scambiato per monete nel negozio.
 */
@Entity
@DiscriminatorValue("MATERIAL")
public class Material extends AbstractItem {

    public Material() {}

    public Material(String name, double price) {
        super(name, price);
    }

    @Override
    public Item copy() {
        return new Material(getName(), getPrice());
    }
}