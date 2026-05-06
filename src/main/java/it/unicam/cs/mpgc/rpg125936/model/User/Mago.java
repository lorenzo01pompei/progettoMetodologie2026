package it.unicam.cs.mpgc.rpg125936.model.User;

import it.unicam.cs.mpgc.rpg125936.model.Item.ItemRegistry;
import it.unicam.cs.mpgc.rpg125936.model.Item.Gun; // Assicurati di importare l'arma

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
