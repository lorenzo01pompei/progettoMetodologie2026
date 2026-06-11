package it.unicam.cs.mpgc.rpg125936.service.game;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.PlayerRepository;

/// Classe che si occupa del caricamento del salvataggio di gioco.
public class SaveGameService {

    private final PlayerRepository playerRepository = new PlayerRepository();

    /**
     * Carica il player salvato nel DB e lo inietta nell'istanza di gioco corrente.
     * Se non esiste alcun salvataggio, lo stato di gameSetup rimane invariato.
     *
     * @param gameSetup l'istanza corrente del gioco in cui ripristinare il player
     */
    public void loadFromSave(GameSetupService gameSetup) {
        Player saved = playerRepository.loadPlayer();
        if (saved != null) {
            gameSetup.setPlayer(saved);
        }
    }
}
