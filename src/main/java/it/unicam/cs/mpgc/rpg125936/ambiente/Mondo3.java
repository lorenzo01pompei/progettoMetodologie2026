package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

public class Mondo3 extends Mondo {

    public Mondo3(boolean isUnlocked) {
        super("Mondo 3", isUnlocked);
        setupWorld();
    }

    @Override
    protected void setupWorld() {
        // miniera per il mondo 3: Rame(18%), Argento(10%), Oro(5%)
        this.miniera = new Miniera("Miniera del Mondo 3", 18.0, 10.0, 5.0);
        
        // creazione nemici specifici del mondo 3
        Enemy nemico1 = new Mago("Mago del Gelo");
        Enemy nemico2 = new Colosso("Gigante di Ghiaccio");

        // boss del mondo3
        Enemy boss = new Boss("Re dei Ghiacci");

        this.enemies.add(nemico1);
        this.enemies.add(nemico2);
        this.enemies.add(boss);
    }
}
