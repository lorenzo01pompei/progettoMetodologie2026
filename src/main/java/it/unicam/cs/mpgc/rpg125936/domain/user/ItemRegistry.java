package it.unicam.cs.mpgc.rpg125936.domain.user;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import it.unicam.cs.mpgc.rpg125936.repository.ItemRepository;

import java.util.List;
import java.util.Random;

/**
 * Gestisce l'accesso casuale agli item di combattimento.
 * per equipaggiare i nemici in fase di inizializzazione del world.
 */
public class ItemRegistry {

    private static final Random random = new Random();

    /// restituisce una copia casuale di un'arma da fuoco dal catalogo
    public static FightItem getRandomGun() {
        List<Gun> guns = new ItemRepository<>(Gun.class).findAll();
        if (guns.isEmpty()) return null;
        return (FightItem) guns.get(random.nextInt(guns.size())).copy();
    }

    /// restituisce una copia casuale di un incantesimo dal catalogo
    public static FightItem getRandomSpell() {
        List<Spell> spells = new ItemRepository<>(Spell.class).findAll();
        if (spells.isEmpty()) return null;
        return (FightItem) spells.get(random.nextInt(spells.size())).copy();
    }
}
