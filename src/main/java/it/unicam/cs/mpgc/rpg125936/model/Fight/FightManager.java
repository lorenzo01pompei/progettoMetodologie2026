package it.unicam.cs.mpgc.rpg125936.model.Fight;

import it.unicam.cs.mpgc.rpg125936.model.Item.FightItem;
import it.unicam.cs.mpgc.rpg125936.model.Item.Item;
import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class FightManager {

    private Random random;

    private Player originalPlayer;
    private Enemy originalEnemy;

    private Player battlePlayer;
    private Enemy battleEnemy;

    private boolean active;

    public FightManager() {
        this.random = new Random();
    }

    /**
     * Inizia una nuova sessione di combattimento clonando le entità.
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
     * Esegue un round di combattimento con l'arma scelta.
     * @param inventoryIndex L'indice dell'arma nell'inventario del player da usare in questo turno.
     * @return true se il combattimento continua, false se uno dei due è morto.
     */
    public boolean playRound(int inventoryIndex) {
        if (!active || battlePlayer.getHealth() <= 0 || battleEnemy.getHealth() <= 0) {
            endFight();
            return false;
        }

        // Recupera l'arma scelta dall'inventario clonato del player
        FightItem playerItem = null;
        if (inventoryIndex >= 0 && inventoryIndex < battlePlayer.getInventory().size()) {
            Item item = battlePlayer.getInventory().get(inventoryIndex);
            if (item instanceof FightItem) {
                playerItem = (FightItem) item;
            }
        }

        // Turno del player
        if (playerItem != null) {
            playerItem.useInFight(battleEnemy);
        }

        // Se l'enemy muore dopo l'attacco del player
        if (battleEnemy.getHealth() <= 0) {
            System.out.println("L'enemy è stato sconfitto!");
            endFight();
            return false;
        }

        // Turno dell'enemy
        FightItem enemyItem = getRandomFightItem(battleEnemy);
        if (enemyItem != null) {
            enemyItem.useInFight(battlePlayer);
        }

        // Se il player muore dopo l'attacco dell'enemy
        if (battlePlayer.getHealth() <= 0) {
            System.out.println("Il player è stato sconfitto!");
            endFight();
            return false;
        }

        // Il combattimento continua
        return true;
    }

    private void endFight() {
        this.active = false;
        // la salute e le vite vengono salvate sul player originale alla fine del fight
        originalPlayer.setHealth((int) battlePlayer.getHealth());
        originalPlayer.setLives(battlePlayer.getLives());
    }

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

    public Player getBattlePlayer() {
        return battlePlayer;
    }

    public Enemy getBattleEnemy() {
        return battleEnemy;
    }
}
