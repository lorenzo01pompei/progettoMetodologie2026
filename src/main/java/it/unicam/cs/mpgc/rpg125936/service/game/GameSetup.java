package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.location.Lobby;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo1;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo2;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo3;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;

import java.util.ArrayList;
import java.util.List;

public class GameSetup {

    private static GameSetup instance;

    private Player player;
    private final Lobby lobby;
    private final List<Mondo> worlds;

    private GameSetup() {
        this.lobby = new Lobby();
        this.worlds = new ArrayList<>();
        this.player = new Player("Eroe", 100);
        initWorlds();
    }

    private void initWorlds() {
        worlds.add(new Mondo1(true));
        worlds.add(new Mondo2(true));
        worlds.add(new Mondo3(true));
    }

    public static GameSetup getInstance() {
        if (instance == null) {
            instance = new GameSetup();
        }
        return instance;
    }

    public static void reset() {
        instance = new GameSetup();
    }

    public static void loadFromSave() {
        if (instance == null) {
            instance = new GameSetup();
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
