package it.unicam.cs.mpgc.rpg125936.service.fight;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class LootService {

    /**
     * Restituisce la lista di armi presenti nell'inventario
     * del nemico sconfitto e che possono essere raccolte dal giocatore.
     *
     * @param enemy il nemico sconfitto da cui estrarre il loot
     * @return lista di FightItem droppati (mai null)
     */
    public List<FightItem> getDroppableItems(Enemy enemy) {
        List<FightItem> loot = new ArrayList<>();
        for (Item item : enemy.getInventory()) {
            if (item instanceof FightItem) {
                loot.add((FightItem)item);
            }
        }
        return loot;
    }

    /**
     * Aggiunge una copia dell'arma all'inventario del giocatore.
     * Utilizza il Prototype pattern (copy()) per non condividere riferimenti.
     *
     * @param player il giocatore che raccoglie l'arma
     * @param item   l'arma da raccogliere
     */
    public void collectItem(Player player, FightItem item) {
        player.addItem(item.copy());
    }
}
