package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.location.Lobby;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo1;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo2;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo3;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopInitializer;

import java.util.ArrayList;
import java.util.List;

public class GameSetupService {

    private static GameSetupService instance;

    private Player player;
    private final Lobby lobby;
    private final List<Mondo> worlds;

    private GameSetupService() {
        ShopInitializer shopInit = new ShopInitializer();
        this.lobby = new Lobby(shopInit.create());
        this.worlds = new ArrayList<>();
        this.player = new Player("Eroe", 100);
        initWorlds();
    }

    private void initWorlds() {
        WorldInitializer initializer = new WorldInitializer();
        Mondo mondo1 = new Mondo1(true);
        Mondo mondo2 = new Mondo2(true);
        Mondo mondo3 = new Mondo3(true);
        initializer.init(mondo1);
        initializer.init(mondo2);
        initializer.init(mondo3);
        worlds.add(mondo1);
        worlds.add(mondo2);
        worlds.add(mondo3);
    }

    public static GameSetupService getInstance() {
        if (instance == null) {
            instance = new GameSetupService();
        }
        return instance;
    }

    public static void reset() {
        instance = new GameSetupService();
    }

    public static void loadFromSave() {
        if (instance == null) {
            instance = new GameSetupService();
        }
        PlayerRepository repo = new PlayerRepository();
        Player saved = repo.loadPlayer();
        if (saved != null) {
            instance.player = saved;
        }
    }

    public Player getPlayer() {
        return player;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public List<Mondo> getWorlds() {
        return worlds;
    }
}
