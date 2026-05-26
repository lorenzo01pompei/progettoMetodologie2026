package it.unicam.cs.mpgc.rpg125936.repository;

import it.unicam.cs.mpgc.rpg125936.domain.item.Gun;
import org.hibernate.Session;

import java.util.List;

public class GunRepository {

    public List<Gun> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Gun", Gun.class).list();
        }
    }

    public Gun findById(long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Gun.class, id);
        }
    }
}
