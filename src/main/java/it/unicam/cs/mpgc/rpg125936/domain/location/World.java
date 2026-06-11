package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * Location di combattimento. Ogni mondo concreto (Mondo1, Mondo2, Mondo3)
 * definisce la propria miniera con le proprie probabilità e caricando i propri nemici
 */
public abstract class World implements GameLocation {

    private final String name;
    private boolean unlocked;
    private List<Enemy> enemies;
    private Mine mine;

    public World(String name, boolean isUnlocked) {
        this.name = name;
        this.unlocked = isUnlocked;
        this.enemies = new ArrayList<>();
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }


    public List<Enemy> getEnemies() {
        return enemies;
    }

    public void setEnemies(List<Enemy> enemies) {
        this.enemies = new ArrayList<>(enemies);
    }

    public Mine getMine() {
        return mine;
    }

    protected void setMine(Mine mine) {
        this.mine = mine;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean canBeMined() {
        return false;
    }

    public List<Enemy> createDefaultEnemies() {
        return List.of();
    }

}
