package it.unicam.cs.mpgc.rpg125936.repository;

import org.hibernate.Session;

import java.util.List;

/**
 * Repository generico per gli item del gioco.
 * Usa il tipo concreto Java come discriminante: Hibernate lo mappa
 * automaticamente sulla colonna {@code item_type} della tabella {@code items}.
 *
 * @param <T> il tipo concreto dell'item (es. {@code Gun}, {@code Spell}, {@code Pickaxe})
 */
public class ItemRepository<T> {

    private final Class<T> type;

    /**
     * @param type il tipo concreto dell'item da gestire
     */
    public ItemRepository(Class<T> type) {
        this.type = type;
    }

    /**
     * Restituisce tutti gli item del tipo specificato
     */
    public List<T> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM " + type.getSimpleName(), type).list();
        }
    }

    /**
     * Restituisce solo gli item del catalogo, ovvero quelli non assegnati
     * ad alcun utente, utile per non ricaricare nel negozio le armi che
     * il player aveva gia acquistato
     */
    public List<T> findCatalog() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery(
                    "SELECT * FROM items WHERE item_type = :itemType AND user_id IS NULL",
                    type
            ).setParameter("itemType", type.getSimpleName().toUpperCase()).list();
        }
    }

}
