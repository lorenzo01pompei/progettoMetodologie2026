package it.unicam.cs.mpgc.rpg125936.service;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.User;

import java.util.Scanner;

public class LootManager {

    public void handleDrop(User player, Enemy defeatedEnemy) {
        FightItem bestWeapon = null;
        for (Item item : defeatedEnemy.getInventory()) {
            if (item instanceof FightItem) {
                FightItem current = (FightItem) item;
                if (bestWeapon == null || current.getDamage() > bestWeapon.getDamage()) {
                    bestWeapon = current;
                }
            }
        }

        if (bestWeapon != null) {
            System.out.println("Il nemico ha droppato la sua arma migliore: " + bestWeapon.getName()
                    + " (Danno: " + bestWeapon.getDamage() + ")");
            System.out.println("Vuoi raccoglierla? (s/n)");
            Scanner scanner = new Scanner(System.in);
            String input = "n";
            try {
                if (scanner.hasNextLine()) {
                    input = scanner.nextLine();
                } else {
                    input = "s";
                }
            } catch (Exception e) {
                input = "s";
            }
            if (input.equalsIgnoreCase("s")) {
                player.addItem(bestWeapon.copy());
                System.out.println("Hai aggiunto l'arma al tuo inventario.");
            } else {
                System.out.println("Hai ignorato l'arma.");
            }
        }
    }
}
