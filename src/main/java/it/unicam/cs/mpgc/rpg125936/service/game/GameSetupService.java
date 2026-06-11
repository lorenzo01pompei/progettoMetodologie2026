package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.location.Lobby;
import it.unicam.cs.mpgc.rpg125936.domain.location.World;
import it.unicam.cs.mpgc.rpg125936.domain.location.World1;
import it.unicam.cs.mpgc.rpg125936.domain.location.World2;
import it.unicam.cs.mpgc.rpg125936.domain.location.World3;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe singleton che gestisce la configurazione iniziale del gioco.
 * Crea il giocatore, la lobby con lo shop e tutti i mondi,
 * inizializzando ciascuno con i propri nemici e miniere.
 */
public class GameSetupService {

    private static GameSetupService instance;

    private Player player;
    private final Lobby lobby;
    private final List<World> worlds;

    /// Inizializza shop, lobby, player di default e chiama initWorlds().

    private GameSetupService() {
        ShopInitializer shopInit = new ShopInitializer();
        this.lobby = new Lobby(shopInit.create());
        this.worlds = new ArrayList<>();
        this.player = new Player("Eroe", 100);
        initWorlds();
    }

    /// delega l'inizializzazione dei mondi del gioco

    private void initWorlds() {
        WorldInitializer initializer = new WorldInitializer();
        World world1 = new World1(true);
        World world2 = new World2(true);
        World world3 = new World3(true);
        initializer.init(world1);
        initializer.init(world2);
        initializer.init(world3);
        worlds.add(world1);
        worlds.add(world2);
        worlds.add(world3);
    }

    /**
     * Restituisce l'istanza di GameSetupService,
     * creandola se necessario
     */
    public static GameSetupService getInstance() {
        if (instance == null) {
            instance = new GameSetupService();
        }
        return instance;
    }

    /**
     * Resetta una nuova istanza.
     * Utile per ricominciare una nuova partita senza riavviare l'app.
     */
    public static void reset() {
        instance = new GameSetupService();
    }


    public Player getPlayer() {
        return player;
    }

    /**
     * Imposta il player corrente.
     * usato per ripristinare il player da un salvataggio.
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    public Lobby getLobby() {
        return lobby;
    }

    public List<World> getWorlds() {
        return worlds;
    }
}
