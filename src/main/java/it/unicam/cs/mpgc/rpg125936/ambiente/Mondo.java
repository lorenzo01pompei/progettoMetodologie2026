package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;

import java.util.ArrayList;
import java.util.List;

public abstract class Mondo implements GameLocation {
    private String name;
    private boolean unlocked;
    protected List<Enemy> enemies;
    protected Miniera miniera;

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

    public Miniera getMiniera() {
        return miniera;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void enter(Player player) {
        if (!unlocked) {
            System.out.println("La porta per il " + name + " è ancora bloccata!");
            return;
        }
        System.out.println("Sei entrato nel " + name + ".");
    }

    @Override
    public void executeAction(Player player) {
        // Logica generale di un mondo (es. mostra il menu: 1. Combatti, 2. Vai in Miniera, 3. Torna alla Lobby)
        System.out.println("Ti trovi nel " + name + ". Ci sono " + enemies.size() + " nemici da sconfiggere.");
    }

    /**
     * Factory Method che ogni mondo concreto deve implementare.
     * Serve a istanziare la propria Miniera e i propri Nemici specifici.
     */
    protected abstract void setupWorld();
}
