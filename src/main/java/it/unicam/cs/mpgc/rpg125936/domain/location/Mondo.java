package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;

import java.util.ArrayList;
import java.util.List;

/**
 * Location di combattimento. Ogni mondo concreto (Mondo1, Mondo2, Mondo3)
 * definisce la propria miniera; i nemici possono essere configurati sovrascrivendo
 * {@link #createDefaultEnemies()} (di default ritorna lista vuota).
 */
public abstract class Mondo implements GameLocation {

    private final String name;
    private boolean unlocked;
    private List<Enemy> enemies;
    private Miniera miniera;

    public Mondo(String name, boolean isUnlocked) {
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

    public Miniera getMiniera() {
        return miniera;
    }

    protected void setMiniera(Miniera miniera) {
        this.miniera = miniera;
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
