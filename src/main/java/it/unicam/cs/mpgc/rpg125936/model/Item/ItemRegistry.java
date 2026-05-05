package it.unicam.cs.mpgc.rpg125936.model.Item;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Classe che simula un Database o un file XML per la persistenza degli oggetti.
 * In futuro, questa logica verrà sostituita da query Hibernate.
 */
public class ItemRegistry {
    private static final List<Gun> guns = new ArrayList<>();
    private static final List<Spell> spells = new ArrayList<>();
    private static final Random random = new Random();

    static {
        // Popoliamo il "database" di armi fisiche
        guns.add(new Gun("Pugnale Arrugginito", 10.0, 5.0));
        guns.add(new Gun("Spada di Ferro", 25.0, 15.0));
        guns.add(new Gun("Ascia da Battaglia", 40.0, 30.0));
        guns.add(new Gun("Spada dell'Eroe", 75.0, 100.0));

        // Popoliamo il "database" di magie
        spells.add(new Spell("Scintilla", 15.0));
        spells.add(new Spell("Palla di Fuoco", 35.0));
        spells.add(new Spell("Fulmine", 50.0));
        spells.add(new Spell("Meteora", 90.0));
    }

    public static Gun getRandomGun() {
        int index = random.nextInt(guns.size());
        return (Gun) guns.get(index).copy(); // Restituiamo una copia per non modificare il "database"
    }

    public static Spell getRandomSpell() {
        int index = random.nextInt(spells.size());
        return (Spell) spells.get(index).copy();
    }
}
