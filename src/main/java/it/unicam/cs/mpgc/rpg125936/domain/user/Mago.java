package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.repository.ItemRegistry;

public class Mago extends Enemy {

    public Mago(String name) {
        super(name);
        this.setHealth(60);

        // 1. Aggiungiamo UNA sola magia
        this.addItem(ItemRegistry.getRandomSpell());

        // 2. Aggiungiamo un'arma normale per quando la magia sarà finita
        // (Puoi usare ItemRegistry se hai un metodo apposito, oppure crearla a mano)
        this.addItem(ItemRegistry.getRandomGun());
        this.addItem(ItemRegistry.getRandomGun());
    }
}
