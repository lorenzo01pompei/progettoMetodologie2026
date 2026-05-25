package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.FightItem;
import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import org.hibernate.Session;

import java.util.List;
import java.util.Random;

public class ItemRegistry {

    private static final Random random = new Random();

    public static FightItem getRandomGun() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Gun> guns = session.createQuery("FROM Gun", Gun.class).list();
            if (guns.isEmpty()) return null;
            int index = random.nextInt(guns.size());
            return (FightItem) guns.get(index).copy();
        }
    }

    public static FightItem getRandomSpell() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Spell> spells = session.createQuery("FROM Spell", Spell.class).list();
            if (spells.isEmpty()) return null;
            int index = random.nextInt(spells.size());
            return (FightItem) spells.get(index).copy();
        }
    }
}
