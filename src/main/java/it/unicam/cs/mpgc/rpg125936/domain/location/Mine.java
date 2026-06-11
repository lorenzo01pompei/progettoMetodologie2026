package it.unicam.cs.mpgc.rpg125936.domain.location;

/**
 * Location che puo essere minata dal giocatore utilizzando un Piccone.
 * Definisce le probabilita di ottenere rame, argento e oro durante l'estrazione.
 */
public class Mine implements GameLocation {
    private final String name;

    private final double copperProb;
    private final double silverProb;
    private final double goldProb;

    public Mine(String name, double copperProb, double silverProb, double goldProb) {
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

}
