package it.unicam.cs.mpgc.rpg125936.domain.health;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

@Embeddable
public class Health {

    @Column(name = "current_health")
    private int health;
    @Column(name = "lives")
    private int lives;
    @Column(name = "initial_health", updatable =false)
    private int initialHealth;

    public Health(int health, int lives){
        this.health = health;
        this.lives = lives;
        this.initialHealth = health;
    }

    public Health(){}

    public int getInitialHealth() {
        return initialHealth;
    }
    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    public int getHpPrice() {
        return 1*(this.getInitialHealth()-this.getHealth());
    }
    public int getLivesPrice() {
        return 40;
    }

    public void decreaseHealth(int damage){
        this.health -= damage;
    }

    public boolean isAlive(){
        return this.health > 0;
    }

    public void resetHealth(){
        this.health = initialHealth;
    }

}
