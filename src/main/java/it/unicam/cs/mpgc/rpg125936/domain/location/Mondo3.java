package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

public class Mondo3 extends Mondo {

    public Mondo3(boolean isUnlocked) {
        super("Mondo 3", isUnlocked);
    }

    @Override
    protected void createDefaultEnemies() {
        this.miniera = new Miniera("Miniera del Mondo 3", 18.0, 10.0, 5.0);

        Enemy nemico1 = new Mago("Mago del Gelo");
        Enemy nemico2 = new Colosso("Gigante di Ghiaccio");
        Enemy boss = new Boss("Re dei Ghiacci");

        this.enemies.add(nemico1);
        this.enemies.add(nemico2);
        this.enemies.add(boss);
    }
}
