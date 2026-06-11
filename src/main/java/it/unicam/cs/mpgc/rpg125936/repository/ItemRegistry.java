package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;

import java.util.List;
import java.util.Random;

/**
 * Registro statico per l'accesso casuale agli item di combattimento.
 * Ogni chiamata apre una sessione Hibernate per caricare il catalogo corrente.
 * Usato da {@link it.unicam.cs.mpgc.rpg125936.domain.user.Enemy#equipDefault()}
 * per equipaggiare i nemici in fase di inizializzazione del mondo.
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
