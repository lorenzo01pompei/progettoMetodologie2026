package it.unicam.cs.mpgc.rpg125936.core;

import it.unicam.cs.mpgc.rpg125936.ambiente.Miniera;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.service.FightManager;
import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Mago;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.domain.user.User;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    // 1. Dichiariamo le variabili di istanza
    private User player;
    private User nemico1;
    private Item piccone;
    private Item arma1;
    private Item arma2;
    private List<Item> inventario;
    private FightManager fightManager;
    private Miniera miniera;

    // 2. Costruttore: qui dentro mettiamo tutte le istruzioni
    public GameEngine() {
        player = new Player("PersonaggioIniziale", 100);

        piccone = new Pickaxe();
        arma1 = new Gun("ak-77", 34, 60);
        arma2 = new Gun("deagle", 13, 35);

        inventario = new ArrayList<>();

        // Ora gli add() e setInventory() funzionano senza errori!
        inventario.add(piccone);
        inventario.add(arma1);
        inventario.add(arma2);

        player.setInventory(inventario);

        nemico1 = new Mago("mago1");
        // Aggiungiamo un'arma al nemico per testare il drop
        nemico1.addItem(new Gun("Bastone Oscuro", 50, 100));

        fightManager = new FightManager();

        System.out.println("\n--- INIZIO COMBATTIMENTO ---");
        fightManager.startFight((Player)player,(Enemy)nemico1);

        // Continua a combattere finché uno dei due non muore
        while (fightManager.playRound(1)) {
            // ... il combattimento continua ...
        }

        System.out.println("\n--- INVENTARIO GIOCATORE DOPO IL COMBATTIMENTO ---");
        for (Item item : player.getInventory()) {
            if (item instanceof FightItem) {
                System.out.println("Arma: " + ((FightItem) item).getName() + " (Danno: " + ((FightItem) item).getDamage() + ")");
            } else {
                System.out.println("Oggetto: " + item.getClass().getSimpleName());
            }
        }

        // Test Miniera
        System.out.println("\n--- TEST MINIERA ---");
        miniera = new Miniera("Miniera d'Oro", 6.0, 2.0, 1.0);
        miniera.enter((Player) player);

        System.out.println("Provo a scavare 5 volte...");
        for (int i = 0; i < 5; i++) {
            miniera.executeAction((Player) player);
        }

        System.out.println("\n--- RISULTATO MATERIALI ---");
        List<List<Material>> materials = player.getMaterials();
        if (materials.isEmpty()) {
            System.out.println("Nessun materiale trovato.");
        } else {
            for (List<Material> list : materials) {
                if (!list.isEmpty()) {
                    String materialName = list.get(0).getName();
                    int count = list.size();
                    System.out.println("Materiale: " + materialName + " - Quantità: " + count);
                }
            }
        }
    }
}