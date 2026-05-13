package it.unicam.cs.mpgc.rpg125936.domain.health;

public class Health {
    private double health;
    private int lives;
    private final double initialHealth;

    public Health(double health, int lives){
        this.health = health;
        this.lives = lives;
        this.initialHealth = health;
    }

    public double getHealth() {
        return health;
    }

    public void setHealth(double health) {
        this.health = health;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public void decreaseHealth(double damage){
        this.health -= damage;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public void resetHealth(){
        this.health = initialHealth;
    }

}
