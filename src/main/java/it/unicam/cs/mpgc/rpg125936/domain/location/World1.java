package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

import java.util.List;

/**
 * Mondo di difficolta base. Probabilita di minerali basse
 * e nemici da aggiungere
 */
public class World1 extends World {

    private static final double COPPER_PROB = 6.0;
    private static final double SILVER_PROB = 2.0;
    private static final double GOLD_PROB = 1.0;

    public World1(boolean isUnlocked) {
        super("Mondo1", isUnlocked);
        setMine(new Mine("Miniera del Mondo 1", COPPER_PROB, SILVER_PROB, GOLD_PROB));
    }

    @Override
    public List<Enemy> createDefaultEnemies() {
        return List.of(
                new Mago("Stregone Oscuro"),
                new Colosso("Gigante di Pietra"),
                new Boss("Il Re delle Caverne")
        );
    }
}
