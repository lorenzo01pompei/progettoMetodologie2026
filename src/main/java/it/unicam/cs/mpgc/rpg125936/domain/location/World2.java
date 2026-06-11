package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

import java.util.List;

/**
 * Mondo di difficolta media. Probabilita di minerali medie
 *  e nemici da aggiungere
 */
public class World2 extends World {

    private static final double COPPER_PROB = 12.0;
    private static final double SILVER_PROB = 5.0;
    private static final double GOLD_PROB = 2.0;

    public World2(boolean isUnlocked) {
        super("Mondo 2", isUnlocked);
        setMine(new Mine("Miniera del Mondo 2", COPPER_PROB, SILVER_PROB, GOLD_PROB));
    }

    /// da implementare
    @Override
    public List<Enemy> createDefaultEnemies() {
        return List.of();
    }
}
