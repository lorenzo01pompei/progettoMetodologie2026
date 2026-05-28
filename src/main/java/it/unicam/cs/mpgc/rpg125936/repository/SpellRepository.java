package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import org.hibernate.Session;

import java.util.List;

public class SpellRepository {

    public List<Spell> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery("SELECT * FROM items WHERE item_type = 'SPELL' AND user_id IS NULL", Spell.class).list();
        }
    }

    public Spell findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Spell.class, id);
        }
    }
}
