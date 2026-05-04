package it.unicam.cs.mpgc.rpg125936.model.Item;

import it.unicam.cs.mpgc.rpg125936.ambiente.GameLocation;
import it.unicam.cs.mpgc.rpg125936.model.User.Player;

public class Pickaxe implements ToolItem {

    private double durability;

    public Pickaxe(){
        this.durability = 500;
    }

    public double getDurability() {
        return durability;
    }

    public void setDurability(double durability) {
        this.durability = durability;
    }

    public void decreaseDurability(){
        if(durability!=0){
            this.durability-=1;
        }
    }
    public void interact(Player player, GameLocation miniera){}

}
