package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.model.User.Boss;
import it.unicam.cs.mpgc.rpg125936.model.User.Colosso;
import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Mago;

public class Mondo1 extends Mondo {

    public Mondo1(boolean isUnlocked) {
        super("Mondo 1", isUnlocked);
        setupWorld();
    }

    @Override
    protected void setupWorld() {
        // viene istanziata la miniera specifica per il mondo 1 passandole le probabilità: Rame(3%), Argento(1%), Oro(0.5%)
        this.miniera = new Miniera("Miniera del Mondo 1", 3.0, 1.0, 0.5);
        
        // creazione dei nemici specifici del mondo 1

        Enemy nemico1 = new Mago("Stregone Oscuro");
        Enemy nemico2 = new Colosso("Gigante di Pietra");
        
        // Boss del Mondo 1
        Enemy boss = new Boss("Il Re delle Caverne");

        // aggiunta dei nemici al mondo
        this.enemies.add(nemico1);
        this.enemies.add(nemico2);
        this.enemies.add(boss);
    }
}
