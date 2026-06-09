package it.unicam.cs.mpgc.rpg125936.domain.item;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;

/**
 * Classe astratta che estrae il campo comune {@code damage} condiviso da Gun e Spell,
 * evitando di duplicarlo in entrambe le sottoclassi.
 */
@Entity
public abstract class AbstractFightItem extends AbstractItem implements FightItem {

    @Column(name = "damage")
    private double damage;

    public AbstractFightItem(String name, double damage, double price) {
        super(name, price);
        this.damage = damage;
    }

    public AbstractFightItem() {}

    public double getDamage() {
        return damage;
    }

    public void setDamage(double damage) {
        this.damage = damage;
    }
}
