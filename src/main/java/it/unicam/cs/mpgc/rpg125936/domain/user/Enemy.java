package it.unicam.cs.mpgc.rpg125936.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

/**
 * Nemico affrontabile in combattimento
 * Le sottoclassi definiscono salute e equipaggiamento specifici.
 */
@Entity
@Table(name="enemies")
public abstract class Enemy extends User {

    @Column(name = "defeated")
    private boolean defeated;

    @Column(name = "world_name")
    private String worldName;

    public Enemy(String name) {
        super(name);
    }

    public Enemy(){}

    public boolean isDefeated() {
        return defeated;
    }

    public void setDefeated(boolean defeated) {
        this.defeated = defeated;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }

    /**
     * Equipaggia il nemico con gli item di default per il suo tipo.
     * Le sottoclassi sovrascrivono questo metodo per definire il proprio loadout,
     * eliminando la necessità di type-switch nel layer di servizio (OCP).
     */
    public abstract void equipDefault();
}
