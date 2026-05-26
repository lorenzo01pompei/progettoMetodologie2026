package it.unicam.cs.mpgc.rpg125936.domain.user;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name="enemies")
public class Enemy extends User {

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

    public String getWorldName() {
        return worldName;
    }

    public void setWorldName(String worldName) {
        this.worldName = worldName;
    }
}
