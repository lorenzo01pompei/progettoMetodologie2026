package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.ambiente.Lobby;
import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo;
import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo1;
import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo2;
import it.unicam.cs.mpgc.rpg125936.ambiente.Mondo3;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

import java.util.ArrayList;
import java.util.List;

public class GameSetup {

    private final Player player;
    private final Lobby lobby;
    private final List<Mondo> worlds;

    public GameSetup() {
        this.player = new Player("Eroe", 100);
        this.lobby = new Lobby();

        Mondo1 mondo1 = new Mondo1(true);
        Mondo2 mondo2= new Mondo2(true);
        Mondo3 mondo3 = new Mondo3(true);

        this.worlds= new ArrayList<>();

        this.worlds.add(mondo1);
        this.worlds.add(mondo2);
        this.worlds.add(mondo3);
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
