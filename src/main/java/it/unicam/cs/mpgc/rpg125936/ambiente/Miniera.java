package it.unicam.cs.mpgc.rpg125936.ambiente;

import it.unicam.cs.mpgc.rpg125936.model.Item.Item;
import it.unicam.cs.mpgc.rpg125936.model.Item.ToolItem;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;
import it.unicam.cs.mpgc.rpg125936.model.Item.Material;
import java.util.Random;

public class Miniera implements GameLocation {
    private final String name;
    protected final Random random;
    
    private final double copperProb;
    private final double silverProb;
    private final double goldProb;

    public Miniera(String name, double copperProb, double silverProb, double goldProb) {
        this.name = name;
        this.copperProb = copperProb;
        this.silverProb = silverProb;
        this.goldProb = goldProb;
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
        // cerca un ToolItem nell'inventario del player e tenta di interagirci
        boolean hasMined = false;
        for (Item item : player.getInventory()) {
            if (item instanceof ToolItem) {
                ToolItem tool = (ToolItem) item;
                if (tool.interact(this)) {
                    hasMined = true;
                    break;
                }
            }
        }

        if (!hasMined) {
            System.out.println("Non hai uno strumento utilizzabile! Torna alla Lobby per comprarne o ripararne uno.");
            return;
        }

        System.out.println("Hai scavato...");

        // le probabilità di ottenere un certo materiale dipendono dai parametri
        double chance = random.nextDouble() * 100;

        // Le probabilità sono gestite ad intervalli cumulativi
        if (chance < goldProb) {
            System.out.println("Hai trovato un Lingotto d'Oro!");
            player.addMaterial(new Material("Lingotto d'Oro", 10.0));
        } else if (chance < goldProb + silverProb) {
            System.out.println("Hai trovato un Lingotto d'Argento!");
            player.addMaterial(new Material("Lingotto d'Argento", 5.0));
        } else if (chance < goldProb + silverProb + copperProb) {
            System.out.println("Hai trovato del Rame.");
            player.addMaterial(new Material("Lingotto di Rame", 2.0));
        } else {
            System.out.println("Nulla di fatto, solo sassi...");
        }
    }
}