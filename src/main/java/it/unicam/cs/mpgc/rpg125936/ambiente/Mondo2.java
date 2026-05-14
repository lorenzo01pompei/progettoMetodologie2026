package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.domain.user.Boss;
import it.unicam.cs.mpgc.rpg125936.domain.user.Colosso;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;

public class Mondo2 extends Mondo {

    public Mondo2(boolean isUnlocked) {
        super("Mondo 2", isUnlocked);
        setupWorld();
    }

    @Override
    protected void setupWorld() {
        // miniera per il mondo 2: Rame(12%), Argento(5%), Oro(2%)
        this.miniera = new Miniera("Miniera del Mondo 2", 12.0, 5.0, 2.0);
        
        // creazione nemici specifici del mondo 2
        Enemy nemico1 = new Mago("Mago di Fuoco");
        Enemy nemico2 = new Colosso("Golem di Magma");

        // creazione boss del mondo 3
        Enemy boss = new Boss("Signore del Vulcano");

        this.enemies.add(nemico1);
        this.enemies.add(nemico2);
        this.enemies.add(boss);
    }
}
