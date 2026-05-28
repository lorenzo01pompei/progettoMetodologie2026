package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class DatabaseSeeder {

    public static void seed() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction t = session.beginTransaction();

        session.createNativeQuery("DELETE FROM items WHERE item_type IN ('GUN','SPELL','PICKAXE') AND user_id IS NULL")
                .executeUpdate();

        session.persist(new Gun("Pugnale Arrugginito", 10.0, 5.0));
        session.persist(new Gun("Spada di Ferro", 25.0, 15.0));
        session.persist(new Gun("Ascia da Battaglia", 40.0, 30.0));
        session.persist(new Gun("Spada dell'Eroe", 75.0, 100.0));
        session.persist(new Spell("Scintilla", 15.0, 10.0));
        session.persist(new Spell("Palla di Fuoco", 35.0, 25.0));
        session.persist(new Spell("Fulmine", 50.0, 40.0));
        session.persist(new Spell("Meteora", 90.0, 80.0));
        session.persist(new Pickaxe());

        t.commit();
        session.close();
    }
}
