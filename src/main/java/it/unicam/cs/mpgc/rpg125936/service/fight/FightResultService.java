package it.unicam.cs.mpgc.rpg125936.service.fight;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.EnemyRepository;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;

public class FightResultService {

    private final PlayerRepository playerRepository = new PlayerRepository();
    private final EnemyRepository enemyRepository = new EnemyRepository();

    public Player handleVictory(Player player, Enemy enemy) {
        enemy.setDefeated(true);
        enemyRepository.save(enemy);
        for (Item item : enemy.getInventory()) {
            if (item instanceof FightItem fi) {
                player.addItem(fi.copy());
            }
        }
        return playerRepository.save(player);
    }

    public Player handleDefeat(Player player, Enemy enemy) {
        playerRepository.save(player);
        enemyRepository.save(enemy);
        return player;
    }

    public Player handleGiveUp(Player player) {
        player.decreaseLives();
        if (player.getLives() <= 0) {
            player.setHealth(0);
        }
        return playerRepository.save(player);
    }
}
