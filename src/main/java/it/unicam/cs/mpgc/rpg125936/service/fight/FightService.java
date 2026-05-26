package it.unicam.cs.mpgc.rpg125936.service.fight;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

///classe che gestisce il combattimento.
public class FightService {

    private Random random;


    private Player battlePlayer;
    private Enemy battleEnemy;

    private boolean active;

    public FightService() {
        this.random = new Random();
    }

    /**inizia una nuova sessione di combattimento
     *
     * @param player l'istanza del giocatore che sta per combattere.
     * @param enemy  l'istanza del nemico da affrontare.
     */
    public void startFight(Player player, Enemy enemy) {

        this.battlePlayer = player;
        this.battleEnemy = enemy;

        this.active = true;
    }

    /**avvia un round di combattimento con l'arma scelta tra quelle nell'inventario.
     *
     * @param inventoryIndex l'indice dell'arma nell'inventario del player da usare in questo turno.
     * @return una stringa con il log del round, oppure null se il combattimento è terminato.
     */
    public String playRound(int inventoryIndex) {
        if (!active || battlePlayer.getHealthStatus().getHealth() <= 0 || battleEnemy.getHealthStatus().getHealth() <= 0) {
            endFight();
            return null;
        }

        StringBuilder log = new StringBuilder();

        //scelta arma
        FightItem playerItem = null;
        if (inventoryIndex >= 0 && inventoryIndex < battlePlayer.getInventory().size()) {
            Item item = battlePlayer.getInventory().get(inventoryIndex);
            if (item instanceof FightItem fi) {
                playerItem = fi;
            }
        }

        //turno player
        if (playerItem != null) {
            String msg = playerItem.useInFight(battleEnemy, battlePlayer.getName());
            log.append(msg);
        }

        if (battleEnemy.getHealthStatus().getHealth() <= 0) {
            endFight();
            return null;
        }

        //turno enemy
        FightItem enemyItem = getRandomFightItem(battleEnemy);
        if (enemyItem != null) {
            String msg = enemyItem.useInFight(battlePlayer, battleEnemy.getName());
            log.append("\n").append(msg);

            if (enemyItem instanceof Spell) {
                battleEnemy.getInventory().remove(enemyItem);
            }
        }

        if (battlePlayer.getHealthStatus().getHealth() <= 0) {
            endFight();
            return null;
        }

        return log.toString();
    }

    /**
     * Termina la sessione di combattimento in corso.
     * Se il player è morto, scala una vita e ripristina la salute (se ha ancora vite).
     * Le statistiche del nemico non vengono sincronizzate,
     * permettendo il ripristino al riavvio del combattimento.
     */
    private void endFight() {
        this.active = false;


        if (battlePlayer.getHealthStatus().getHealth() <= 0) {
            int remainingLife=battlePlayer.getLives()-1;
            battlePlayer.setLives(remainingLife);
            if (remainingLife > 0) {
                battlePlayer.setHealth(100);
            } else {
                battlePlayer.setHealth(0);
            }
        }

        if(battlePlayer.getHealthStatus().getLives()==0){
            battlePlayer.setHealth(0);
        }

        if (battleEnemy.getHealthStatus().getHealth() <= 0) {
            battleEnemy.setHealth(0);
        }



    }

    /**
     * Seleziona casualmente un oggetto di tipo FightItem dall'inventario del nemico.
     *
     * @param enemy Il nemico da cui estrarre l'arma.
     * @return Il FightItem scelto casualmente, oppure null se il nemico non ha item.
     */
    private FightItem getRandomFightItem(Enemy enemy) {
        List<FightItem> fightItems = new ArrayList<>();
        for (Item item : enemy.getInventory()) {
            if (item instanceof FightItem) {
                fightItems.add((FightItem) item);
            }
        }

        // L'enemy non ha armi per combattere
        if (fightItems.isEmpty()) {
            return null;
        }

        //scelta casuale dell'arma
        int index = random.nextInt(fightItems.size());
        return fightItems.get(index);
    }

    /**
     * Restituisce l'istanza clonata del Player che sta attualmente combattendo.
     * @return Il battlePlayer.
     */
    public Player getBattlePlayer() {
        return battlePlayer;
    }

    /**
     * Restituisce l'istanza clonata dell'Enemy che sta attualmente combattendo.
     * @return Il battleEnemy.
     */
    public Enemy getBattleEnemy() {
        return battleEnemy;
    }

    /**
     * Restituisce l'istanza originale del nemico affrontato.
     * Utile per accedere al loot (inventario) dopo la sconfitta.
     *
     * @return il nemico originale (non clonato)

    public Enemy getOriginalEnemy() {
        return originalEnemy;
    }
*/
}
