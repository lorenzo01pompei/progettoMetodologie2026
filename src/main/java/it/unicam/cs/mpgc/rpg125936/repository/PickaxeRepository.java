package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.Pickaxe;
import org.hibernate.Session;

import java.util.List;

public class PickaxeRepository {

    public List<Pickaxe> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createNativeQuery("SELECT * FROM items WHERE item_type = 'PICKAXE' AND user_id IS NULL", Pickaxe.class).list();
        }
    }

    public Pickaxe findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Pickaxe.class, id);
        }
    }
}
