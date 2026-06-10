package it.unicam.cs.mpgc.rpg125936.domain.item;

import it.unicam.cs.mpgc.rpg125936.domain.user.User;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.Iterator;

/**
 * Incantesimo da combattimento che riduce il danno delle armi nemiche
 * Se il danno di un'arma scende a zero, l'arma viene
 * distrutta e rimossa dall'inventario del bersaglio.
 */
@Entity
@DiscriminatorValue("SPELL")
public class Spell extends AbstractFightItem {

    public Spell(String name, double damage, double price) {
        super(name, damage, price);
    }

    public Spell(){}

    @Override
    public String useInFight(User target, String attackerName){
        StringBuilder feedback = new StringBuilder(attackerName + " lancia " + getName() + ": il danno delle armi dell'avversario viene ridotto di " + getDamage() + " punti!");
        Iterator<Item> iterator = target.getInventory().iterator();
        while (iterator.hasNext()) {
            Item item = iterator.next();
            if (item instanceof FightItem fi) {
                if (fi.reduceDamage(getDamage())) {
                    iterator.remove();
                    feedback.append("\n").append(attackerName).append(" ha distrutto l'arma ").append(fi.getName()).append(" di ").append(target.getName()).append(", non puo piu essere utilizzata");
                }
            }
        }
        return feedback.toString();
    }

    @Override
    public boolean isConsumedAfterUse() {
        return true;
    }

    @Override
    public Item copy() {
        return new Spell(getName(), getDamage(), getPrice());
    }
}
