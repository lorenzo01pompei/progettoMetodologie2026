package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.location.Lobby;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo1;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo2;
import it.unicam.cs.mpgc.rpg125936.domain.location.Mondo3;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.service.shop.ShopInitializer;

import java.util.ArrayList;
import java.util.List;

/**
 * Service singleton che gestisce la configurazione iniziale del gioco.
 * Crea il giocatore, la lobby con lo shop e tutti i mondi,
 * inizializzando ciascuno con i propri nemici e miniere.
 */
public class GameSetupService {

    private static GameSetupService instance;

    private Player player;
    private final Lobby lobby;
    private final List<Mondo> worlds;

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

    /**
     * Restituisce l'istanza di GameSetupService,
     * creandola se necessario
     *
     * @return istanza singleton di GameSetupService
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

    /**
     * @return il giocatore corrente
     */
    public Player getPlayer() {
        return player;
    }

    /**
     * Imposta il player corrente.
     * usato per ripristinare il player da un salvataggio.
     *
     * @param player il player da ripristinare
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * @return la lobby del gioco, contenente lo shop
     */
    public Lobby getLobby() {
        return lobby;
    }

    /**
     * @return lista dei mondi disponibili
     */
    public List<Mondo> getWorlds() {
        return worlds;
    }
}
