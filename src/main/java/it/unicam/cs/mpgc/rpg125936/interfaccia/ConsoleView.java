package it.unicam.cs.mpgc.rpg125936.interfaccia;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.mine.MiningResultDTO;

import java.util.List;

public class ConsoleView {

    public void printFightStart() {
        System.out.println("\n--- INIZIO COMBATTIMENTO ---");
    }

    public void printLoot(List<FightItem> drops) {
        System.out.println("\n--- LOOT NEMICO ---");
        for (FightItem drop : drops) {
            System.out.println("- " + drop.getName() + " (Danno: " + drop.getDamage() + ") [Raccogli]");
        }
    }

    public void printInventory(Player player) {
        System.out.println("\n--- INVENTARIO GIOCATORE DOPO IL COMBATTIMENTO ---");
        for (Item item : player.getInventory()) {
            if (item instanceof FightItem fi) {
                System.out.println("Arma: " + fi.getName() + " (Danno: " + fi.getDamage() + ")");
            } else {
                System.out.println("Oggetto: " + item.getClass().getSimpleName());
            }
        }
    }

    public void printMiningStart() {
        System.out.println("\n--- TEST MINIERA ---");
        System.out.println("Sei entrato nella Miniera d'Oro. Prepara il piccone!");
        System.out.println("Provo a scavare 5 volte...");
    }

    public void printMiningResults(List<MiningResultDTO> results) {
        System.out.println("\n--- RISULTATO MATERIALI ---");
        for (MiningResultDTO result : results) {
            System.out.println(result.getMessage());
        }

        System.out.println("\n--- RIEPILOGO MATERIALI ---");
    }

    public void printMaterialSummary(Player player) {
        List<List<Material>> materials = player.getMaterials();
        if (materials.isEmpty()) {
            System.out.println("Nessun materiale trovato.");
            return;
        }
        for (List<Material> list : materials) {
            if (!list.isEmpty()) {
                System.out.println("Materiale: " + list.get(0).getName() + " - Quantità: " + list.size());
            }
        }
    }
}
