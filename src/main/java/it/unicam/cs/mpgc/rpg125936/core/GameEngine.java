package it.unicam.cs.mpgc.rpg125936.core;

import it.unicam.cs.mpgc.rpg125936.model.Fight.FightManager;
import it.unicam.cs.mpgc.rpg125936.model.Item.FightItem;
import it.unicam.cs.mpgc.rpg125936.model.Item.Gun;
import it.unicam.cs.mpgc.rpg125936.model.Item.Item;
import it.unicam.cs.mpgc.rpg125936.model.Item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.model.User.Enemy;
import it.unicam.cs.mpgc.rpg125936.model.User.Mago;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;
import it.unicam.cs.mpgc.rpg125936.model.User.User;

import java.util.ArrayList;
import java.util.List;

public class GameEngine {
    // 1. Dichiariamo le variabili di istanza
    private User player;
    private User nemico1;
    private Item piccone;
    private Item arma1;
    private Item arma2;
    private List<Item> inventario;
    private FightManager fightManager;

    // 2. Costruttore: qui dentro mettiamo tutte le istruzioni
    public GameEngine() {
        player = new Player("PersonaggioIniziale", 100);

        piccone = new Pickaxe();
        arma1 = new Gun("ak-77", 34, 60);
        arma2 = new Gun("deagle", 13, 35);

        inventario = new ArrayList<>();

        // Ora gli add() e setInventory() funzionano senza errori!
        inventario.add(piccone);
        inventario.add(arma1);
        inventario.add(arma2);

        player.setInventory(inventario);

        nemico1 = new Mago("mago1");

        fightManager = new FightManager();

        fightManager.startFight((Player)player,(Enemy)nemico1);

        for (int i =0; i<30; i++){
            fightManager.playRound(1);
        }



    }
}