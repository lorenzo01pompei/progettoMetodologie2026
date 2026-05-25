package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Player;

public class Miniera implements GameLocation {
    private final String name;
    
    private final double copperProb;
    private final double silverProb;
    private final double goldProb;

    public Miniera(String name, double copperProb, double silverProb, double goldProb) {
        this.name = name;
        this.copperProb = copperProb;
        this.silverProb = silverProb;
        this.goldProb = goldProb;
    }

    @Override
    public boolean canBeMined() {
        return true;
    }

    @Override
    public String getName() {
        return name;
    }

    public double getCopperProb() {
        return copperProb;
    }

    public double getSilverProb() {
        return silverProb;
    }

    public double getGoldProb() {
        return goldProb;
    }

    @Override
    public void enter(Player player) {
        System.out.println("Sei entrato nella " + name + ". Prepara il piccone!");
    }




}