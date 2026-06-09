package it.unicam.cs.mpgc.rpg125936.domain.health;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;

/**
 * Gestisce i punti vita e le vite di un giocatore/nemico.
 * Tiene traccia della salute corrente, della salute iniziale (per calcolare i costi di cura)
 * e del numero di vite rimanenti. Fornisce metodi per subire danno e calcolare il prezzo
 * del recupero, separando la logica di salute dalla logica di gioco.
 */
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

}
