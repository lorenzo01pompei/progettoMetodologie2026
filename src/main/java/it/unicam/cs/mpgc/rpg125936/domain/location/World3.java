package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

import java.util.List;

/**
 * Mondo di difficolta alta. Probabilita di minerali alte e
 * nemici
 */
public class World3 extends World {

    private static final double COPPER_PROB = 18.0;
    private static final double SILVER_PROB = 10.0;
    private static final double GOLD_PROB = 5.0;

    public World3(boolean isUnlocked) {
        super("Mondo 3", isUnlocked);
        setMine(new Mine("Miniera del Mondo 3", COPPER_PROB, SILVER_PROB, GOLD_PROB));
    }

    /// da implementare
    @Override
    public List<Enemy> createDefaultEnemies() {
        return List.of();
    }
}
