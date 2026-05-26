package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

public class Mondo1 extends Mondo {

    public Mondo1(boolean isUnlocked) {
        super("Mondo 1", isUnlocked);
    }

    @Override
    protected void createDefaultEnemies() {
        this.miniera = new Miniera("Miniera del Mondo 1", 6.0, 2.0, 1.0);

        Enemy nemico1 = new Mago("Stregone Oscuro");
        Enemy nemico2 = new Colosso("Gigante di Pietra");
        Enemy boss = new Boss("Il Re delle Caverne");

        this.enemies.add(nemico1);
        this.enemies.add(nemico2);
        this.enemies.add(boss);
    }
}
