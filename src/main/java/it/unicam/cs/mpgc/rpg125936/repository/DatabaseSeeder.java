package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import it.unicam.cs.mpgc.rpg125936.domain.item.Spell;
import org.hibernate.Session;
import org.hibernate.Transaction;

/// Popola il database con gli item di default del gioco.
public class DatabaseSeeder {

    /**
     * cancella i dati esistenti e reinserisce gli item di default
     * all'interno di un'unica transazione.
     */
    public static void seed() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Transaction t = session.beginTransaction();

            clearItems(session);
            insertDefaultItems(session);
            t.commit();

        }
    }

    /// cancella dal DB tutti gli item del catalogo (non associati a nessun utente)
    private static void clearItems(Session session) {
        session.createNativeMutationQuery(
            "DELETE FROM items WHERE item_type IN ('GUN','SPELL','PICKAXE') AND user_id IS NULL"
        ).executeUpdate();
    }

    /// inserisce il set di armi, incantesimi e strumenti di default
    private static void insertDefaultItems(Session session) {
        session.persist(new Gun("Pugnale Arrugginito", 10.0, 5.0));
        session.persist(new Gun("Spada di Ferro", 20.0, 15.0));
        session.persist(new Gun("Ascia da Battaglia", 35.0, 30.0));
        session.persist(new Gun("Spada dell'Eroe", 60.0, 90.0));
        session.persist(new Spell("Scintilla", 15.0, 10.0));
        session.persist(new Spell("Fulmine", 20.0, 40.0));
        session.persist(new Spell("Veleno", 30.0, 80.0));
        session.persist(new Pickaxe());
    }
}
