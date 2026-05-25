package it.unicam.cs.mpgc.rpg125936.domain.health;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Health {

    @Column(name = "current_health")
    private double health;
    @Column(name = "lives")
    private int lives;
    @Column(name = "initial_health", updatable =false)
    private double initialHealth;

    public Health(double health, int lives){
        this.health = health;
        this.lives = lives;
        this.initialHealth = health;
    }

    public Health(){}

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

    public int getLivesPrice() {
        return 40;
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
