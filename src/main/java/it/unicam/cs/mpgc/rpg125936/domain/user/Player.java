package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.Item;
import it.unicam.cs.mpgc.rpg125936.domain.material.Material;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="players")
public class Player extends User{

    @Column(name = "money")
    private double money;

    public Player(String name, double initialMoney) {
        super(name);
        this.money = initialMoney;
    }

    public Player(){}

    public void addMoney(double amount) { this.money += amount; }

    public double getMoney() {
        return money;
    }

    public void decreaseLives(){
        if(this.getLives()!=0){
            this.setLives(this.getLives()-1);
        }

    }

    public void setMoney(double money) {
        this.money = money;
    }


}