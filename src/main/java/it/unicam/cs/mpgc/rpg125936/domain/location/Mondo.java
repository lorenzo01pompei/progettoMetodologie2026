package it.unicam.cs.mpgc.rpg125936.domain.location;

import it.unicam.cs.mpgc.rpg125936.domain.user.Enemy;
import it.unicam.cs.mpgc.rpg125936.domain.user.Player;
import it.unicam.cs.mpgc.rpg125936.repository.EnemyRepository;
import it.unicam.cs.mpgc.rpg125936.repository.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.util.ArrayList;
import java.util.List;

public abstract class Mondo implements GameLocation {

    protected static final EnemyRepository enemyRepo = new EnemyRepository();

    private String name;
    private boolean unlocked;
    protected List<Enemy> enemies;
    protected Miniera miniera;

    public Mondo(String name, boolean isUnlocked) {
        this.name = name;
        this.unlocked = isUnlocked;
        this.enemies = new ArrayList<>();
        initWorld();
    }

    private void initWorld() {
        List<Enemy> existing = enemyRepo.findByWorld(name);
        if (!existing.isEmpty()) {
            this.enemies = existing;
        } else {
            createDefaultEnemies();
            try (Session session = HibernateUtil.getSessionFactory().openSession()) {
                Transaction t = session.beginTransaction();
                for (Enemy e : enemies) {
                    e.setWorldName(name);
                    session.persist(e);
                }
                t.commit();
            }
        }
    }

    public boolean isUnlocked() {
        return unlocked;
    }

    public void setUnlocked(boolean unlocked) {
        this.unlocked = unlocked;
    }

    public List<Enemy> getEnemies() {
        return enemies;
    }

    public Miniera getMiniera() {
        return miniera;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean canBeMined() {
        return false;
    }

    @Override
    public void enter(Player player) {
        if (!unlocked) {
            System.out.println("La porta per il " + name + " è ancora bloccata!");
            return;
        }
        System.out.println("Sei entrato nel " + name + ".");
    }

    protected abstract void createDefaultEnemies();
}
