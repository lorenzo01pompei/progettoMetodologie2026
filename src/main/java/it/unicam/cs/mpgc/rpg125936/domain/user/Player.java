package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Giocatore controllato dall'utente.
 * Gestisce le caratteristiche base di User, il suo denaro e lo sblocco dei mondi.
 */
@Entity
@Table(name="players")
public class Player extends User{

    public static final int MAX_LIVES = 3;

    @Column(name = "money")
    private double money;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "player_unlocked_worlds", joinColumns = @JoinColumn(name = "player_id"))
    @Column(name = "world_name") // Una sola colonna per il nome del world
    private Set<String> unlockedWorlds;


    public Player(String name, double initialMoney) {
        super(name);
        this.money = initialMoney;
        this.unlockedWorlds = new HashSet<>();
    }

    public Player(){}

    public void addMoney(double amount) { this.money += amount; }

    public double getMoney() {
        return money;
    }
    public void setMoney(double money) {
        this.money = money;
    }


    public void decreaseLives(){
        if(this.getLives()!=0){
            this.setLives(this.getLives()-1);
        }
    }

    public void handleDeath() {
        int remainingLives = getLives() - 1;
        setLives(remainingLives);
        setHealth(remainingLives > 0 ? getHealthStatus().getInitialHealth() : 0);
    }

    public boolean isWorldUnlocked(String worldName) {
        return this.unlockedWorlds.contains(worldName);
    }

    public void unlockWorld(String worldName) {
        this.unlockedWorlds.add(worldName);
    }




}