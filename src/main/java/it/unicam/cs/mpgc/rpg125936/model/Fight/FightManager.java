package it.unicam.cs.mpgc.rpg125936.model.Fight;

import it.unicam.cs.mpgc.rpg125936.model.Item.FightItem;
import it.unicam.cs.mpgc.rpg125936.model.Item.Item;
import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe responsabile della gestione delle sessioni di combattimento.
 * Utilizza il pattern Prototype (tramite clonazione) per creare istanze isolate
 * di Player ed Enemy ad ogni scontro, preservando le statistiche originali
 * in caso di sconfitta o fuga.
 */
public class FightManager {

    private Random random;

    private Player originalPlayer;
    private Enemy originalEnemy;

    private Player battlePlayer;
    private Enemy battleEnemy;

    private boolean active;

    /**
     * Costruttore base di FightManager.
     * Inizializza il generatore di numeri casuali.
     */
    public FightManager() {
        this.random = new Random();
    }

    /**
     * Inizia una nuova sessione di combattimento clonando le entità per evitare che
     * i dati modificati durante un combattimento impattino le classi originali.
     *
     * @param player L'istanza del giocatore originale che sta per combattere.
     * @param enemy  L'istanza del nemico originale da affrontare.
     */
    public void startFight(Player player, Enemy enemy) {
        this.originalPlayer = player;
        this.originalEnemy = enemy;

        // viene creata un'istanza isolata per il combattimento in modo che
        // danni alle armi e vita del nemico si resettino
        // per i combattimenti successivi
        this.battlePlayer = player.copy();
        this.battleEnemy = enemy.copy();

        this.active = true;
    }

    /**
     * Avvia un round di combattimento con l'arma scelta tra quelle nell'inventario.
     *
     * @param inventoryIndex L'indice dell'arma nell'inventario del player da usare in questo turno.
     * @return true se il combattimento continua, false se uno dei due è morto e lo scontro è terminato.
     */
    public boolean playRound(int inventoryIndex) {
        if (!active || battlePlayer.getHealth() <= 0 || battleEnemy.getHealth() <= 0) {
            endFight();
            return false;
        }

        // viene recuperata l'arma scelta dall'inventario clonato del player
        FightItem playerItem = null;
        if (inventoryIndex >= 0 && inventoryIndex < battlePlayer.getInventory().size()) {
            Item item = battlePlayer.getInventory().get(inventoryIndex);
            if (item instanceof FightItem) {
                playerItem = (FightItem) item;
            }
        }

        // turno del player
        if (playerItem != null) {
            playerItem.useInFight(battleEnemy);
        }

        // se la vita dell'enemy scende a/o sotto lo zero finisce il fight
        if (battleEnemy.getHealth() <= 0) {
            System.out.println("L'enemy è stato sconfitto!");
            endFight();
            return false;
        }

        // turno dell'enemy
        FightItem enemyItem = getRandomFightItem(battleEnemy);
        if (enemyItem != null) {
            enemyItem.useInFight(battlePlayer);
        }

        // se la vita del player scende a/o sotto lo zero finisce il fight
        if (battlePlayer.getHealth() <= 0) {
            System.out.println("Il player è stato sconfitto!");
            endFight();
            return false;
        }

        // il round è terminato con entrambi gli attacchi e si può procedere al prossimo
        return true;
    }

    /**
     * Termina la sessione di combattimento in corso.
     * Si occupa di sincronizzare la salute e le vite perse sul Player originale.
     * Le statistiche del nemico (es. la vita persa) non vengono sincronizzate,
     * permettendo il ripristino al riavvio del combattimento.
     */
    private void endFight() {
        this.active = false;
        // la salute e le vite vengono salvate sul player originale alla fine del fight
        originalPlayer.setHealth((int) battlePlayer.getHealth());
        originalPlayer.setLives(battlePlayer.getLives());
    }

    /**
     * Seleziona casualmente un oggetto di tipo FightItem dall'inventario del nemico.
     *
     * @param enemy Il nemico da cui estrarre l'arma.
     * @return Il FightItem scelto casualmente, oppure null se il nemico non ne possiede.
     */
    private FightItem getRandomFightItem(Enemy enemy) {
        List<FightItem> fightItems = new ArrayList<>();
        for (Item item : enemy.getInventory()) {
            if (item instanceof FightItem) {
                fightItems.add((FightItem) item);
            }
        }
        
        if (fightItems.isEmpty()) {
            return null; // L'enemy non ha armi per combattere
        }

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
}
