package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.model.User.Player;
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
       /*
        if (player.getPickaxeDurability() <= 0) {
            System.out.println("Il tuo piccone è rotto! Torna alla Lobby per ripararlo o comprarne uno nuovo.");
            return;
        }

        */

        /*
        // 1. Consuma durabilità
        player.reducePickaxe();
        System.out.println("Hai scavato... (Durabilità residua: " + player.getPickaxeDurability() + ")");

         */

        // 2. Calcola il drop in base al livello del mondo
        double chance = random.nextDouble() * 100; // Valore tra 0.0 e 100.0

        if (isGoldFound(chance)) {
            System.out.println("Incredibile! Hai trovato dell'ORO! +10 monete");
            player.addMoney(10);
        } else if (isSilverFound(chance)) {
            System.out.println("Ottimo! Hai trovato dell'ARGENTO! +5 monete");
            player.addMoney(5);
        } else if (isCopperFound(chance)) {
            System.out.println("Hai trovato del RAME. +2 monete");
            player.addMoney(2);
        } else {
            System.out.println("Nulla di fatto, solo sassi...");
        }
    }

    // Metodi privati per gestire le probabilità specifiche per ogni mondo
    private boolean isCopperFound(double chance) {
        return switch (worldLevel) {
            case 1 -> chance <= 3.0;
            case 2 -> chance <= 8.0;
            case 3 -> chance <= 15.0;
            default -> false;
        };
    }

    private boolean isSilverFound(double chance) {
        return switch (worldLevel) {
            case 1 -> chance <= 1.0;
            case 2 -> chance <= 4.0;
            case 3 -> chance <= 10.0;
            default -> false;
        };
    }

    private boolean isGoldFound(double chance) {
        return switch (worldLevel) {
            case 1 -> chance <= 0.5;
            case 2 -> chance <= 1.5;
            case 3 -> chance <= 4.0;
            default -> false;
        };
    }
}