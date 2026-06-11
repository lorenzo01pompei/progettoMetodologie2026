package it.unicam.cs.mpgc.rpg125936.service.fight;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe che gestisce il combattimento a turni tra un giocatore e un nemico.
 */
public class FightService {

    private final Random random = new Random();
    private Player battlePlayer;
    private Enemy battleEnemy;
    private boolean active;

    public FightService() {
    }

    /**
     * Avvia una nuova sessione di combattimento tra giocatore e nemico.
     * @param player il giocatore che combatte
     * @param enemy  il nemico da affrontare
     */
    public void startFight(Player player, Enemy enemy) {
        this.battlePlayer = player;
        this.battleEnemy = enemy;
        this.active = true;
    }

    /**
     * Esegue un round di combattimento.
     * Fasi: attacca il giocatore con l'arma selezionata; se il nemico e ancora
     * in vita, risponde il nemico.
     *
     * @param inventoryIndex indice dell'arma nell'inventario del giocatore da usare
     * @return log testuale del round, oppure {@code null} se il combattimento e terminato
     */
    public String playRound(int inventoryIndex) {
        if (!active || isPlayerDead() || isEnemyDead()) {
            endFight();
            return null;
        }

        StringBuilder log = new StringBuilder();
        executePlayerTurn(inventoryIndex, log);
        if (isEnemyDead()) {
            endFight();
            return null;
        }

        executeEnemyTurn(log);
        if (isPlayerDead()) {
            endFight();
            return null;
        }

        return log.toString();
    }

    /// esegue il turno del giocatore: recupera l'arma dall'inventario e la usa contro il nemico
    /// e la rimuove se consumata (incantesimi monouso)
    private void executePlayerTurn(int inventoryIndex, StringBuilder log) {
        FightItem weapon = getPlayerWeapon(inventoryIndex);
        if (weapon != null) {
            log.append(weapon.useInFight(battleEnemy, battlePlayer.getName()));
            removeIfConsumed(weapon, battlePlayer);
        }
    }

    /// esegue il turno del nemico: sceglie un'arma casuale dall'inventario, la usa contro il giocatore
    /// e la rimuove se consumata (incantesimi monouso)
    private void executeEnemyTurn(StringBuilder log) {
        FightItem weapon = getRandomEnemyWeapon();
        if (weapon != null) {
            log.append("\n").append(weapon.useInFight(battlePlayer, battleEnemy.getName()));
            removeIfConsumed(weapon, battleEnemy);
        }
    }

    /// seleziona casualmente un FightItem dall'inventario del nemico
    private FightItem getRandomEnemyWeapon() {
        List<FightItem> weapons = new ArrayList<>();
        for (Item item : battleEnemy.getInventory()) {
            if (item instanceof FightItem fi) {
                weapons.add(fi);
            }
        }
        if (weapons.isEmpty()) return null;
        return weapons.get(random.nextInt(weapons.size()));
    }

    /// recupera l'arma del giocatore dall'inventario in base all'indice,
    /// restituisce null se l'indice e fuori range o l'item non e un FightItem
    private FightItem getPlayerWeapon(int index) {
        if (index < 0 || index >= battlePlayer.getInventory().size()) return null;
        Item item = battlePlayer.getInventory().get(index);
        return (item instanceof FightItem fi) ? fi : null;
    }

    /// rimuove dall'inventario le armi che vanno consumate dopo l'uso
    private void removeIfConsumed(FightItem weapon, it.unicam.cs.mpgc.rpg125936.domain.user.User user) {
        if (weapon.isConsumedAfterUse()) {
            user.getInventory().remove(weapon);
        }
    }

    /**
     * Termina la sessione di combattimento.
     * Se il giocatore e morto, ne delega la perdita di una vita
     * Se il nemico e morto, azzera i suoi HP per segnalarne la sconfitta.
     */
    private void endFight() {
        this.active = false;
        if (isPlayerDead()) {
            battlePlayer.handleDeath();
        }
        if (isEnemyDead()) {
            battleEnemy.getHealthStatus().setHealth(0);
        }
    }

    /// true se il giocatore non ha piu HP
    private boolean isPlayerDead() {
        return battlePlayer.getHealthStatus().getHealth() <= 0;
    }

    /// true se il nemico non ha piu HP
    private boolean isEnemyDead() {
        return battleEnemy.getHealthStatus().getHealth() <= 0;
    }

    /**
     * Restituisce l'istanza del giocatore che sta combattendo,
     * utile al controller per leggere HP e vite aggiornati.
     */
    public Player getBattlePlayer() {
        return battlePlayer;
    }

    /**
     * Restituisce l'istanza del nemico che sta combattendo,
     * utile al controller per leggere HP aggiornati.
     */
    public Enemy getBattleEnemy() {
        return battleEnemy;
    }
}
