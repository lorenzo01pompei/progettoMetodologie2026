package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.model.User.Player;
import it.unicam.cs.mpgc.rpg125936.model.Item.Material;
import java.util.Random;

public class Miniera implements GameLocation {
    private final String name;
    private final int worldLevel;
    private final Random random;

    public Miniera(int worldLevel) {
        this.worldLevel = worldLevel;
        this.name = "Miniera del Mondo " + worldLevel;
        this.random = new Random();
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public void enter(Player player) {
        System.out.println("Sei entrato nella " + name + ". Prepara il piccone!");
    }

    /**
     * L'azione principale della miniera è scavare.
     */
    @Override
    public void executeAction(Player player) {
        // tentativo di utilizzo del primo piccone disponibile
        if (!player.usePickaxe()) {
            System.out.println("Non hai un piccone utilizzabile! Torna alla Lobby per comprarne o ripararne uno.");
            return;
        }

        System.out.println("Hai scavato...");

        // le probabilità di ottenere un certo materiale dipende dal mondo
        double chance = random.nextDouble() * 100;

        double goldProb = getGoldProb();
        double silverProb = getSilverProb();
        double copperProb = getCopperProb();

        // Le probabilità sono gestite ad intervalli (0 a goldProb -> oro, goldProb a gold+silver -> argento, ecc.)
        if (chance < goldProb) {
            System.out.println("Hai trovato un Lingotto d'Oro!");
            player.addItem(new Material("Lingotto d'Oro", 10.0));
        } else if (chance < goldProb + silverProb) {
            System.out.println("Hai trovato un Lingotto d'Argento!");
            player.addItem(new Material("Lingotto d'Argento", 5.0));
        } else if (chance < goldProb + silverProb + copperProb) {
            System.out.println("Hai trovato del Rame.");
            player.addItem(new Material("Lingotto di Rame", 2.0));
        } else {
            System.out.println("Nulla di fatto, solo sassi...");
        }
    }

    // Metodi privati per restituire la percentuale esatta di ogni materiale
    private double getCopperProb() {
        return switch (worldLevel) {
            case 1 -> 3.0;
            case 2 -> 8.0;
            case 3 -> 15.0;
            default -> 0.0;
        };
    }

    private double getSilverProb() {
        return switch (worldLevel) {
            case 1 -> 1.0;
            case 2 -> 4.0;
            case 3 -> 10.0;
            default -> 0.0;
        };
    }

    private double getGoldProb() {
        return switch (worldLevel) {
            case 1 -> 0.5;
            case 2 -> 1.5;
            case 3 -> 4.0;
            default -> 0.0;
        };
    }
}