package it.unicam.cs.mpgc.rpg125936.service.fight;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe che gestisce il combattimento.
 * Utilizza il pattern Prototype (tramite clonazione) per creare istanze isolate
 * di Player ed Enemy ad ogni scontro, mantenendo le statistiche originali
 * recuperabili dopo il combattimento.
 */
public class FightService {

    private Random random;

    private Player originalPlayer;
    private Enemy originalEnemy;

    private Player battlePlayer;
    private Enemy battleEnemy;

    private boolean active;

    public FightService() {
        this.random = new Random();
    }

    /**
     * Inizia una nuova sessione di combattimento clonando le entità per evitare che
     * i dati modificati durante un combattimento impattino le classi originali.
     *
     * @param player l'istanza del giocatore originale che sta per combattere.
     * @param enemy  l'istanza del nemico originale da affrontare.
     */
    public void startFight(Player player, Enemy enemy) {
        this.originalPlayer = player;
        this.originalEnemy = enemy;

        this.battlePlayer = player.copy();
        this.battleEnemy = enemy.copy();

        this.active = true;
    }

    /**
     * Avvia un round di combattimento con l'arma scelta tra quelle nell'inventario.
     *
     * @param inventoryIndex l'indice dell'arma nell'inventario del player da usare in questo turno.
     * @return true se il combattimento continua, false se uno dei due è morto e lo scontro è terminato.
     */
    public boolean playRound(int inventoryIndex) {
        if (!active || battlePlayer.getHealth() <= 0 || battleEnemy.getHealth() <= 0) {
            endFight();
            return false;
        }

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
            playerItem.useInFight(battleEnemy);
        }

        if (battleEnemy.getHealth() <= 0) {
            endFight();
            return false;
        }

        //turno enemy
        FightItem enemyItem = getRandomFightItem(battleEnemy);
        if (enemyItem != null) {
            enemyItem.useInFight(battlePlayer);

            if (enemyItem instanceof Spell) {
                battleEnemy.getInventory().remove(enemyItem);
            }
        }

        if (battlePlayer.getHealth() <= 0) {
            endFight();
            return false;
        }

        return true;
    }

    /**
     * Termina la sessione di combattimento in corso.
     * Se il player è morto, scala una vita e ripristina la salute (se ha ancora vite).
     * Le statistiche del nemico non vengono sincronizzate,
     * permettendo il ripristino al riavvio del combattimento.
     */
    private void endFight() {
        this.active = false;

        if (battlePlayer.getHealth() <= 0) {
            int remaining = originalPlayer.getLives() - 1;
            originalPlayer.setLives(remaining);
            battlePlayer.setLives(remaining);
            if (remaining > 0) {
                originalPlayer.setHealth(100);
                battlePlayer.setHealth(100);
            } else {
                originalPlayer.setHealth(0);
                battlePlayer.setHealth(0);
            }
        } else {
            originalPlayer.setHealth((int) battlePlayer.getHealth());
            originalPlayer.setLives(battlePlayer.getLives());
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
     */
    public Enemy getOriginalEnemy() {
        return originalEnemy;
    }

}
